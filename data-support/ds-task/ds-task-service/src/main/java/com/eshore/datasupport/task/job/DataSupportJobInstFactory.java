package com.eshore.datasupport.task.job;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.job.JobEntryResult;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshore.datasupport.common.service.IAlarmNotifyService;
import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.common.util.DateUtils;
import com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.datasupport.task.pojo.Ycjl;
import com.eshore.datasupport.task.service.IRwbService;
import com.eshore.datasupport.task.service.IRwslService;
import com.eshore.datasupport.task.service.IYcjlService;
import com.eshore.datasupport.task.vo.DataSupportJob;
import com.eshore.datasupport.task.vo.DataSupportTrigger;
import com.eshore.datasupport.task.vo.TaskKettleVo;

/**
 * 业务系统调度任务类
 * 
 * @author zhl
 *
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DataSupportJobInstFactory implements org.quartz.Job {
	private final static Logger logger = Logger
			.getLogger(DataSupportJobInstFactory.class);
	@Value("#{configProperties['kettle.user.name']}")
	private String userName;
	@Value("#{configProperties['kettle.user.password']}")
	private String password;

	@Value("#{configProperties['kettle.dataMeta.name']}")
	private String dataMetaName;
	@Value("#{configProperties['kettle.dataMeta.type']}")
	private String dataMetaType;
	@Value("#{configProperties['kettle.dataMeta.access']}")
	private String dataMetaAccess;
	@Value("#{configProperties['kettle.dataMeta.db']}")
	private String dataMetaDb;
	@Value("#{configProperties['kettle.dataMeta.host']}")
	private String dataMetaHost;
	@Value("#{configProperties['kettle.dataMeta.port']}")
	private String dataMetaPort;
	@Value("#{configProperties['kettle.dataMeta.user']}")
	private String dataMetaUser;
	@Value("#{configProperties['kettle.dataMeta.pass']}")
	private String dataMetaPass;
	@Value("#{configProperties['kettle.zntz.month']}")
	private String monthAdjust;
	@Value("#{configProperties['kettle.zntz.year']}")
	private String yearAdjust;

	@Value("#{configProperties['kettle.time.compare']}")
	private Long compareTime;
	/**
	 * 采集和处理 追数时间，单位分钟
	 */
	@Value("#{configProperties['kettle.zntz.chase']}")
	private Integer chaseTime;

	private final SimpleDateFormat ymdhms = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Autowired
	IRwslService rwslService;

	@Autowired
	IRwbService rwbService;

	@Autowired
	IYcjlService ycjlService;
	@Autowired
	IAlarmNotifyService alarmNotifyService;

	DatabaseMeta dataMeta;
	KettleDatabaseRepositoryMeta repInfo;
	static {
		// 初始化k一次环境
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			logger.info(e);
		}
	}

	public DataSupportJobInstFactory() {
		super();
	}

	// TODO 单台job不会同时执行，分布式情况下要考虑
	/** 执行任务入口 */

	@SuppressWarnings("unused")
	@Override
	public void execute(JobExecutionContext jobExcContext)
			throws JobExecutionException {
		JobDataMap jobDataMap = jobExcContext.getMergedJobDataMap();
		String dsJobId = (String) jobDataMap.get("dsJobId");
		// TODO dsJobId从数据库获取KETTLE脚本本次执行的最新参数
		// 查询当前job的实例表成功了的记录，如果有记录，则用实例的参数里面的结束时间 作为开始时间，再加上计算量
		TaskKettleVo tkv = rwbService.findRWJOB(dsJobId);// 获取任务情况

		// 未找到任务配置，移除job
		if (tkv == null) {
			logger.info("未找到任务配置,jobid:" + dsJobId);
//			rwbService.removeJobScheduer(dsJobId);
			return;//
		}
		// 任务未启用
		if (Conts.ZT_N.equals(tkv.getFd_rwzt())) {// 任务未启用
			logger.info("任务未启用,jobid:" + dsJobId);
//			rwbService.removeJobScheduer(dsJobId);
			return;
		}

		/*原执行逻辑， start  如果符合规范执行一次
		// 检查是否满足运行条件
//		if (!checkRun(tkv, dsJobId)) {
//			return;
//		}
		// 执行任务
//		excuteJob(tkv, jobDataMap);
		原执行逻辑， end*/
		
		/*现有执行逻辑，追数据，直到数据提取完*/
		while (checkRun(tkv, dsJobId)) {//如果时间检查合格，循环执行
			if(!excuteJob(tkv, jobDataMap))return ;
			tkv = rwbService.findRWJOB(dsJobId);
		}
	}

	/**
	 * 执行kettle job
	 * 
	 * @param tkv
	 * @param jobDataMap
	 */
	private boolean excuteJob(TaskKettleVo tkv, JobDataMap jobDataMap) {
		// 获取当前任务实例的运行参数
		String dsJobId = (String) jobDataMap.get("dsJobId");
		String rGroupName = (String) jobDataMap.get("kettleGroupName");
		String rJobName = (String) jobDataMap.get("kettleJobName");
		// 获取job 信息
		DataSupportJob dsJob = new DataSupportJob();
		dsJob.setDirectory(rGroupName);// 入参
		dsJob.setKettleJob(rJobName);// 入参
		// 获取trigger 信息
		DataSupportTrigger jobTrigger = new DataSupportTrigger();
		jobTrigger.setJob(dsJob);
		Integer fd_result = 0;
		// 获取时间参数
		String[] paramTimes = getParamTimes(tkv);
		String startTime = paramTimes[0];
		String endTime = paramTimes[1];

		// 实例参数
		String log = "";
		Date startJobTime = new Date();
		Long linesRead = 0L;
		Long linesRejected = 0L;
		String rwslId = UUID.randomUUID().toString();
		try {

			// kettle DB Repository 元对象，可通过配置文件读取 数据库的连接信息 String name, String
			// type, String access, String host, String db, String port, String
			// user, String pass
			// name：连接名称, type：数据库类型：连接方式（kettle支持的连接方式） //access //host：数据库IP
			// //db:资源库实例名 //port:资源库端口 //user:用户名 //pass：用户密码)
			// 并行会产生很多个jdbc连接,任务多了会报错 TODO
			dataMeta = new DatabaseMeta(dataMetaName, dataMetaType,
					dataMetaAccess, dataMetaHost, dataMetaDb, dataMetaPort,
					dataMetaUser, dataMetaPass);
			repInfo = new KettleDatabaseRepositoryMeta();
			repInfo.setConnection(dataMeta);

			// 创建DB资源库
			// 以后考虑移动repository到全局，这样就只会用一个jdbc了。目前使用一个jdbc，任务并行run报错，串行不报错，但是会影响任务执行实时性
			KettleDatabaseRepository repository = new KettleDatabaseRepository();
			repository.init(repInfo);
			// 连接资源库
			repository.connect(userName, password);// 连接资源库的用户名和密码，用户名和密码是在在表R_USER中存储的.先从配置文件获取，

			// 保存作业或转换时可以选择目录
			RepositoryDirectoryInterface repdir = repository
					.findDirectory(jobTrigger.getJob().getDirectory());
			JobMeta jobMeta = repository.loadJob(jobTrigger.getJob()
					.getKettleJob(), repdir, null, null);
			if (jobMeta == null) {
				logger.info("未找到相关job，请检查kettle配置");
				return false;
			}
			org.pentaho.di.job.Job job1 = new org.pentaho.di.job.Job(
					repository, jobMeta);

			// 执行kettle任务，job的参数必须通过job设置,TODO 自动根据入参算出起止时间参数
			job1.setVariable("startTime", startTime);// 入参,根据任务id从数据库获取
			job1.setVariable("endTime", endTime);// 入参，更加任务id从数据库获取
			job1.start();
			job1.waitUntilFinished();

			Result jobRes = job1.getResult();
			log = jobRes.getLogText();
			
			if (jobRes.getNrErrors() <= 0) {// 1 表示执行成功
				fd_result = 1;
				//获取统计数据量结果
				List<JobEntryResult> results = job1.getJobEntryResults();
				for(JobEntryResult jer:results){
					if(!"成功".equals(jer.getJobEntryName())){
						Result result = jer.getResult();
						/*
						 * 要明白Kettle对LINES_READ,LINES_INPUT的定义。
						 * LINES_INPUT:从外部提取到的数据。例如表输入组件，提取的数据量定义为LINES_INPUT中,
						 * LINES_READ:从上一个组件读取到的数据量，例如数据校验组件，统计的数据量定义为LINES_READ
						 */
						long lines_input=result.getNrLinesInput();
						long lines_read = result.getNrLinesRead();
						if(lines_input>=lines_read){
							linesRead+=lines_input;
						}else{
							linesRead+=lines_read;
						}
						
						linesRejected+=result.getNrLinesRejected();
					}
				}
			}else{
				if(log.contains("中止")){
					fd_result=2;
				}
			}

			// 执行成功 保存异常记录  
			saveYcjl(fd_result,linesRejected, rwslId, dsJobId, jobRes.getRows(), tkv);

			// 更新任务表的 数据时间戳
			this.updateSjc(fd_result,startTime, endTime, tkv);
			
			// Now the job task is finished, mark it as finished.
			job1.setFinished(true);
			// Cleanup the parameters used by the job. Post that invoke GC.
			jobMeta.eraseParameters();
			job1.eraseParameters();
			if(fd_result==1){
				return true;
			}else{
				return false;
			}
		} catch (KettleException e) {
			logger.info(e);
			fd_result = 0;
			log = e.toString();
			return false;
		} catch (Exception e) {
			logger.info(e);
			fd_result = 0;
			log = e.toString();
			return false;
		} finally {
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("startTime", startTime);
			paramsMap.put("endTime", endTime);
			Date endJobTime = new Date();
			// 任务实例入库
			Rwsl rwslInst = new Rwsl();
			rwslInst.setFd_rw_id(dsJobId);
			rwslInst.setFd_qdsj(startJobTime);
			rwslInst.setFd_jssj(endJobTime);
			rwslInst.setFd_yxcs(JSON.toJSONString(paramsMap));
			rwslInst.setFd_zxjg(fd_result);
			rwslInst.setId(rwslId);
			if (linesRead > 0 && linesRejected > 0) {
				rwslInst.setFd_cljls(linesRead - linesRejected);// 总记录数-拒绝记录数
			} else {
				rwslInst.setFd_cljls(linesRead);//
			}
			try {
				rwslInst.setFd_bzxx(log.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			rwslInst.setFd_ycjls(linesRejected);// 异常记录数(拒绝的记录数)
			rwslInst.setFd_sjzt("Y");
			rwslInst.setFd_sjc(parseDate(paramsMap.get("startTime")));
			rwslService.save(rwslInst);
			
			//发送告警
			this.sendGj(fd_result, paramsMap, startJobTime, endJobTime, tkv, dsJobId);
		}
	}

 
	/**
	 * 发送告警信息
	 * @param fd_result 任务执行结果
	 * @param paramsDataMap  数据参数
	 * @param startJobTime  任务开始时间
	 * @param endJobTime  任务结束时间
	 * @param tkv 任务Vo
	 * @param dsJobId 任务id
	 */
	private void sendGj(int fd_result,Map<String, String> paramsDataMap,Date startJobTime,Date endJobTime,TaskKettleVo tkv,String dsJobId){
		if (fd_result != 1) {// 任务执行失败 告警
			// 告警内容
			StringBuilder sb = new StringBuilder();
			sb.append("【任务执行失败】");
			sb.append(",规则名称：");
			sb.append(tkv.getGzmc());
			sb.append(",任务执行时间：");
			sb.append(this.ymdhms.format(startJobTime));
			sb.append("-");
			sb.append(this.ymdhms.format(endJobTime));
			sb.append(",任务数据时间：");
			sb.append(paramsDataMap.get("startTime"));
			sb.append("-");
			sb.append(paramsDataMap.get("endTime"));
			// 发送告警
			alarmNotifyService.sendYcInfo(dsJobId, sb.toString());
		}
	}
	
	/**
	 * 更新任务表时间戳
	 * @param fd_result  任务执行结果
	 * @param endTime 任务时间戳时间
	 * @param tkv  任务vo
	 */
	private void updateSjc(int fd_result, String startTime,String endTime, TaskKettleVo tkv) {
		// 计算时间戳,
		if (tkv.getFd_lx() != 2 && fd_result == 1) {// 任务执行成功， 非挖掘不用记录时间戳记录
			// 字段调整 取上一次执行的 数据开始时间戳
			rwbService.updateSJC(startTime,endTime, tkv.getId());// 执行sql语句
			// 如果因为特殊原因，这里未执行的话，实例表和任务表的数据时间戳不一致，实例会继续重新跑一次，实例里面入库要用
		}
	}

	/**
	 * 保存异常记录
	 * 
	 * @param linesRejected
	 *            异常记录数据数目
	 * @param rwslId
	 *            任务实例id
	 * @param dsJobId
	 *            任务ID
	 * @param list
	 *            异常记录数据
	 * @param tkv
	 *            任务vo
	 */
	private void saveYcjl(int fd_result,long linesRejected, String rwslId, String dsJobId,
			List<RowMetaAndData> list, TaskKettleVo tkv) {
		if (linesRejected > 0 && fd_result == 1) {
			Ycjl entity = new Ycjl();
			entity.setId(rwslId);// 使用任务实例ID
			entity.setFd_rw_id(dsJobId);

			List<Map<String, Object>> listMap = getJsonList(list);
			entity.setFd_ycsj(JSON.toJSONString(listMap,SerializerFeature.WriteMapNullValue).getBytes());// 异常数据
			countMissingAndInvalid(listMap,entity);//异常记录统计
			entity.setFd_srb_id(tkv.getFd_srb_id());// 输入表ID
			entity.setFd_srsjy_id(tkv.getFd_srsjy_id());// 输入数据源ID
			long listSize = listMap.size();
			entity.setFd_bz("异常数据记录条数：" + listSize);// 备注
			// 有拒绝的记录
			ycjlService.save(entity);
		}
	}
	
	
	/*
	 * 统计数据缺失率和数据无效性   暂不开放
	 */
	private void countMissingAndInvalid(List<Map<String, Object>> listMap,Ycjl entity){
		Long missingNum = 0l;
		Long invalidNum = 0L;
		for(Map <String,Object >map :listMap){
			String code = (String)map.get("fd_dsjzc_cwbm");
			String column = (String)map.get("fd_dsjzc_cwl");
			String []codes = code.split("\\|");
			String []columns = column.split("\\|");
			//当同一条数据有多个字段空缺时，只算一条;无效数据同理;
			boolean missingFlag = false;
			boolean invalidFlag = false;
			for(int i=0;i<codes.length;i++){
				if(codes[i].equals("DICT_ERR")){
					Object o = map.get(columns[i]);
					if(o==null){
						missingFlag = true;
					}else{
						invalidFlag = true;
					}
				}else if(codes[i].equals("NULL_ERR")){
					missingFlag = true;
				}
			}
			if(missingFlag)missingNum+=1;
			if(invalidFlag)invalidNum+=1;
		}
		entity.setFd_sjqs(missingNum);
		entity.setFd_sjwx(invalidNum);
	}

	/**
	 * 拼装异常数据Map
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> getJsonList(List<RowMetaAndData> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (RowMetaAndData ra : list) {//行记录
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] o = ra.getData();
			List<ValueMetaInterface> lvmi = ra.getRowMeta().getValueMetaList();
			boolean found=false;
			for (int i = 0; i < lvmi.size(); i++) {
				ValueMetaInterface vmi = lvmi.get(i);
				if("fd_dsjzc_cwl".equalsIgnoreCase(vmi.getName())){
					found=true;
				}
				map.put(vmi.getName(), o[i]);
			}
			//没有订阅错误列的数据不保存
			if(found){
			listMap.add(map);
			}
		}
		return listMap;
	}

	/**
	 * 获取数据时间参数
	 * 
	 * @param tkv
	 * @return
	 */
	private String[] getParamTimes(TaskKettleVo tkv) {
		String[] paramTimes = new String[2];
		Integer sjl = tkv.getFd_sjl();// 分钟 默认
		String dw = tkv.getFd_sjldw();
		if (tkv.getFd_lx() != 2) { // 时间超出范围了的话，开始追数据chaseTime
			Date now = new Date();
			Date sjc = tkv.getFd_sjsjc();// 首次取任务 首次数据时间戳
			Date sjc_end = addSjc(sjc, dw, sjl);
			/* 原有追数据逻辑，现改为在外部循环控制执行次数
			if (compareTime(sjc_end, now, chaseTime * 60000L)) {// 结束时间 <
																// now-chaseTime
																// 开始追数据
				sjc_end = addSjc(sjc, Conts.SJLDW_MINITUE, chaseTime);// 单位是
																		// 分钟,传参单位改成分钟
			}
			*/

			paramTimes[0] = ymdhms.format(sjc);// 数据时间戳 开始
			paramTimes[1] = ymdhms.format(sjc_end);

		} else {// 挖掘的时间戳是根据 系统当前时间来计算的 + 智能调整来计算 + 数据量单位 默认应该是凌晨执行
			Date now = new Date();
			if (Conts.SJLDW_MONTH.equals(dw)) {
				String[] x = DateUtils.getMonthStartAndEnd(DateUtils.getMonth(
						now, "-1"));// 上个月初天
				paramTimes[0] = x[0];
				paramTimes[1] = x[1];

			} else if (Conts.SJLDW_YEAR.equals(dw)) {
				String[] x = DateUtils.getYearStartAndEnd(DateUtils.getYear(
						now, "-1"));
				paramTimes[0] = x[0];// 包含startTime
				paramTimes[1] = x[1]; // 不包含endTime
			}

		}

		return paramTimes;
	}

	/**
	 * 通过计量单位换算时间
	 * 
	 * @param date
	 * @param dw
	 * @param sjl
	 * @return
	 */
	public Date addSjc(Date date, String dw, Integer sjl) {
		Date date_temp = null;
		Integer sjl_temp = sjl;
		if (sjl_temp == null) {
			sjl_temp = 0;
		}

		if (Conts.SJLDW_MINITUE.equals(dw)) {// 分钟
			date_temp = DateUtils.getMinute(date, sjl_temp.toString());
		} else if (Conts.SJLDW_MONTH.equals(dw)) {// 月
			date_temp = DateUtils.getMonth(date, sjl_temp.toString());
		} else if (Conts.SJLDW_YEAR.equals(dw)) {// 年
			date_temp = DateUtils.getYear(date, sjl_temp.toString());
		} else if (Conts.SJLDW_DAY.equals(dw)) {// 天
			date_temp = DateUtils.getDay(date, sjl_temp.toString());
		} else if (Conts.SJLDW_WEEK.equals(dw)) {// 周
			date_temp = DateUtils.getWeek(date, sjl_temp.toString());
		}

		return date_temp;
	}

	/**
	 * date1比较date2
	 * 
	 * @param date1
	 *            业务时间
	 * @param now
	 *            now
	 * @param compareTime
	 *            间隔时间(毫秒数 )
	 * @return date1小于(now-间隔时间),返回 true
	 */
	private boolean compareTime(Date date1, Date now, Long compareTime) {
		if (date1 == null || now == null) {
			return false;
		}
		if (date1.getTime() + compareTime < now.getTime()) {// date1时间已经过去
			return true;
		}
		return false;
	}

	/**
	 * 检查采集任务运行条件
	 * 
	 * @param tkv
	 * @param dsJobId
	 * @return
	 */
	private boolean check00(TaskKettleVo tkv) {
		Date now = new Date();
		Integer sjl = tkv.getFd_sjl();// 分钟 默认
		String dw = tkv.getFd_sjldw();
		Date sjc = tkv.getFd_sjsjc();// （首次取任务 首次数据时间戳 ） 本次开始数据时间戳
		Date sjc_end = addSjc(sjc, dw, sjl);// 通过计量单位、计量数量，计算结束数据时间戳

		// 判断当前时间是否大于 数据接收时间戳
		if (!compareTime(sjc_end, now, compareTime)) {
			logger.info("任务执行时间未到！");
			return false;
		}

		return true;
	}

	/**
	 * 检查处理任务运行条件
	 * 
	 * @param tkv
	 * @return
	 */
	private boolean check01(TaskKettleVo tkv) {
		if (!check00(tkv)) {
			return false;
		}

		if (StringUtils.isBlank(tkv.getFd_qzrw_id())) {
			logger.info("任务配置问题，未配置前置任务！");
			return false;
		}

		Integer sjl = tkv.getFd_sjl();// 分钟 默认
		String dw = tkv.getFd_sjldw();
		Date sjc = tkv.getFd_sjsjc();// （首次取任务 首次数据时间戳 ） 本次开始数据时间戳
		Date sjc_end = addSjc(sjc, dw, sjl);// 通过计量单位、计量数量，计算结束数据时间戳
		
		String qzrw_id = tkv.getFd_qzrw_id();
		String []qzrw_ids = qzrw_id.split("#");
		
		for(String id_temp:qzrw_ids){
			if(!checkQzrw(id_temp,sjc_end)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断前置任务是否完成
	 */
	 private boolean checkQzrw(String  id ,Date sjc_end){
		 
		TaskKettleVo rwb = rwbService.findRWJOB(id);// 前置任务时间戳
		if (rwb == null) {// TODO 异常情况，前置任务配置问题
			logger.info("任务配置问题！");
			return false;
		}
		Date sjc_qz_start = rwb.getFd_sjsjc();

		//前置下次开始时间(上次前置任务结束数据时间戳) > 处理任务 结束数据时间戳  + 时间间隔 ； 才执行处理任务
		if (!compareTime(sjc_end,sjc_qz_start,compareTime)) {
			logger.info("前置任务未完成,前置任务id:" + id);// 后置结束时间
			// 大于前置下次开始时间，表示前置还没有采集到后置需要的时间内数据
			return false;
		}
		 return true;
	 }

	/**
	 * 检查挖掘任务运行条件
	 * 
	 * @param tkv
	 * @param dsJobId
	 * @return
	 */
	private boolean check02(TaskKettleVo tkv, String dsJobId) {

		String dw = tkv.getFd_sjldw();

		if (!(Conts.SJLDW_MONTH.equals(dw) || Conts.SJLDW_YEAR.equals(dw))) {
			return false;
		}

		// 智能调整 //挖掘的时间戳，在任务配置表里不记录
		if ("Y".equals(tkv.getFd_zntz())) {// 需要智能调整
			return check02_zntz(tkv);
		} else {// 月头第一天执行，判断当前时间是月初第一天，如果第一天宕机了会有问题，
			return check02_zntz_not(tkv, dsJobId);
		}

	}

	/**
	 * 检查智能调整
	 * 
	 * @param tkv
	 * @return
	 */
	private boolean check02_zntz_not(TaskKettleVo tkv, String dsJobId) {
		Date now = new Date();
		String dw = tkv.getFd_sjldw();
		try {
			if (Conts.SJLDW_MONTH.equals(dw)) {
				//挖掘任务，按月执行时，让其每次执行的时间为2号     wd add，
				DateFormat formatYYYYMMDD=new SimpleDateFormat("yyyyMMdd");
				String yyyymmdd = formatYYYYMMDD.format(now);
				if(yyyymmdd.endsWith("01")){
					return false;
				}
				//end
				String[] x = DateUtils.getMonthStartAndEnd(DateUtils.getMonth(
						now, "-1"));// 上个月初天
				Date startTime = parseDate(x[0]);
				Rwsl rwsl = rwslService.findLastRwsl(dsJobId, startTime);
				if (rwsl != null) {// 改成判断是否当月执行过一次上月的挖掘 查实例表
					logger.info("非智能调整，已经执行过一次");
					return false;
				}
			} else if (Conts.SJLDW_YEAR.equals(dw)) {
				String[] x = DateUtils.getYearStartAndEnd(DateUtils.getYear(
						now, "-1"));// 上年初
				Date startTime = parseDate(x[0]);// 包含startTime
				Rwsl rwsl = rwslService.findLastRwsl(dsJobId, startTime);
				if (rwsl != null) {// 改成判断是否当月执行过一次上月的挖掘 查实例表 ,
					logger.info("非智能调整，已经执行过一次");
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
		return true;
	}

	/**
	 * 检查智能调整
	 * 
	 * @param tkv
	 * @return
	 */
	private boolean check02_zntz(TaskKettleVo tkv) {
		Date now = new Date();
		String dw = tkv.getFd_sjldw();
		if (Conts.SJLDW_MONTH.equals(dw)) {
			if (!monthAdjust.contains("," + DateUtils.getDay(now) + ",")) {
				logger.info("不在智能调整执行时间内");
				return false; // 不在智能调整执行时间内
			}
		} else if (Conts.SJLDW_YEAR.equals(dw)) {
			if (!yearAdjust.contains("," + (DateUtils.getMonth(now) + 1) + ",")) {
				return false; // 不在智能调整执行时间内
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * 检查本次是否可以运行
	 * 
	 * @return
	 */
	private boolean checkRun(TaskKettleVo tkv, String dsJobId) {
		/*
		 * 当在追数据时，如果关闭任务，不会让追加操作停下来。所以在这里判断任务状态，当关闭任务时，也会停止追加任务.  start 
		 */
		if(Conts.ZT_N.equals(tkv.getFd_rwzt())){
			return false;
		}
		/*	end */
		if (tkv.getFd_lx() == 0) {// 采集
			if (!check00(tkv)) {
				return false;
			}
		} else if (tkv.getFd_lx() == 1) {// 处理
			if (!check01(tkv)) {
				return false;
			}
		} else {// 挖掘的时间戳是根据 系统当前时间来计算的 + 智能调整来计算 + 数据量单位 默认应该是凌晨执行
			if (!check02(tkv, dsJobId)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	private Date parseDate(String date) {
		try {
			return this.ymdhms.parse(date);
		} catch (ParseException e) {
			logger.info(e);
		}
		return null;
	}

}
