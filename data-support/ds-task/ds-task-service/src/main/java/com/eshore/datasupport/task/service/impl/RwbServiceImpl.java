package com.eshore.datasupport.task.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.task.dao.IRwbDao;
import com.eshore.datasupport.task.job.DataSupportJobInstFactory;
import com.eshore.datasupport.task.pojo.Rwb;
import com.eshore.datasupport.task.service.IRwbService;
import com.eshore.datasupport.task.vo.TaskKettleVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * 
 * 
 * @author
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class RwbServiceImpl extends BaseServiceImpl<Rwb> implements IRwbService {

	@Autowired
	IRwbDao rwbDao;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Value("#{configProperties['kettle.month.cros']}")
	private String monthDypl;
	@Value("#{configProperties['kettle.year.cros']}")
	private String yearDypl;

	private static final Logger logger = Logger.getLogger(RwbServiceImpl.class);
	
	@Override
	public IBaseDao<Rwb> getDao() {
		return (IBaseDao<Rwb>) rwbDao;
	}

	/**
	 * 在启用时添加到scheduer里面
	 */
	@Override
	public void addJobScheduer(Rwb rwb) {
		JobDataMap jobData = new JobDataMap();
		jobData.put("dsJobId", rwb.getId());// job里面需要用到的参数，任务id

		// jobData.put("triggerId", "1");//job里面需要用到的参数，
		TaskKettleVo tkv = rwbDao.findRWJOB(rwb);
		if (tkv != null) {
			//根据dsJobId 查询到TaskKettleVo的jobId，grouptype传入参数，以免重复执行时多余查询动作
			// 必须保证任务和kettlt对应关系不能变动
			jobData.put("kettleJobId", tkv.getR_job_id());
			jobData.put("kettleJobName", tkv.getR_job_name());
			
			String groupName = getGroupName(tkv);			
			if (StringUtils.isNotBlank(groupName)) {
				jobData.put("kettleGroupName", groupName);
				// 指定用那个jobClass
				JobDetail jobDetail = JobBuilder
						.newJob(DataSupportJobInstFactory.class)
						.setJobData(jobData)
						.withIdentity(tkv.getR_job_name(), groupName).build();// 定义名称和组别,与kettle的job名称和脚本一致

				
				String dlpl=tkv.getFd_ddpl();//存入时自动计算	
				
				TriggerKey triggerKey = TriggerKey.triggerKey(
						tkv.getR_job_name(), groupName);// 定义cron的名称和组别
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
							.cronSchedule(dlpl);//调用频率					
					//设置开始执行的时间
					
					try {//启用时，加入到scheduler
						CronTrigger trigger = TriggerBuilder.newTrigger().startAt(tkv.getFd_scqdsj())
								.withIdentity(triggerKey).withSchedule(scheduleBuilder)
								.build();
						schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,
								trigger);

					} catch (Exception e) {
						logger.info(e);
					}				
			}
		}
	}

	/**
	 * 在启用时添加到scheduer里面
	 */
	@Override
	public void removeJobScheduer(Rwb rwb) {
		TaskKettleVo tkv = rwbDao.findRWJOB(rwb);
		if(tkv!=null){
		String groupName = getGroupName(tkv);
		if (StringUtils.isNotBlank(groupName)) {
			JobKey jobKey=JobKey.jobKey(tkv.getR_job_name(),
					groupName);
			try {
				schedulerFactoryBean.getScheduler().deleteJob(jobKey);//删除job，不影响最后一次正在运行的任务，表中数据都会清理掉
			} catch (SchedulerException e) {
				logger.info(e);
			}
		}
		}
	}

	@Override	
	public TaskKettleVo findRWJOB(String jobId){
		Rwb rwb = this.get(jobId);
		return rwbDao.findRWJOB(rwb);
	}
	@Override	
	 public void updateSJC(String startTime ,String endTime,String id){
		 rwbDao.updateSJC(startTime,endTime,id);
	 }
	
	/**
	 * 解析group名称
	 * 
	 * @param tv
	 * @return
	 */
	public String getGroupName(TaskKettleVo tv) {
		if (tv.getFd_lx()==0) {
			return Conts.GROUP_CJ;
		} else if (tv.getFd_lx()==1) {
			return Conts.GROUP_CL;
		} else if (tv.getFd_lx()==2) {
			return Conts.GROUP_WJ;
		}
		return null;
	}
	
	@Override
	public String changeTaskStatus(String id,String status ) {
		Rwb rwb = this.get(id);
		rwb.setFd_rwzt(status);
		changeQuertzTask(rwb);
		rwb.setFd_rwzt(status);
		this.update(rwb);
		return "success";
	}
	
	private void changeQuertzTask(Rwb rwbRecord){
		if(Conts.ZT_Y.equals(rwbRecord.getFd_rwzt())){
			addJobScheduer(rwbRecord);
		}else {
			removeJobScheduer(rwbRecord);
		}
	}

	@Override
	public void updateAndChangeStatus(Rwb rwb) {
		Rwb rwb_search = this.get(rwb.getId());
		rwb_search.setFd_gz_id(rwb.getFd_gz_id());
		rwb_search.setFd_scqdsj(rwb.getFd_scqdsj());
		rwb_search.setFd_rwzt(rwb.getFd_rwzt());
		rwb_search.setFd_rwms(rwb.getFd_rwms());
		rwb_search.setFd_gxr(rwb.getFd_gxr());
		rwb_search.setFd_gxsj(rwb.getFd_gxsj());
		if(0==rwb.getFd_lx()){
			rwb_search.setFd_ddpl(rwb.getFd_ddpl());
			rwb_search.setFd_sjsjc(rwb.getFd_sjsjc());
			rwb_search.setFd_sjl(rwb.getFd_sjl());
		}else if(1==rwb.getFd_lx()){
			rwb_search.setFd_ddpl(rwb.getFd_ddpl());
			rwb_search.setFd_sjsjc(rwb.getFd_sjsjc());
			rwb_search.setFd_sjl(rwb.getFd_sjl());
			rwb_search.setFd_qzrw_id(rwb.getFd_qzrw_id());
		}else if(2==rwb.getFd_lx()){
			rwb_search.setFd_zntz(rwb.getFd_zntz());
			rwb_search.setFd_sjldw(rwb.getFd_sjldw());
			if(Conts.SJLDW_MONTH.equals(rwb_search.getFd_sjldw())){
				rwb_search.setFd_ddpl(monthDypl);
			}else{
				rwb_search.setFd_ddpl(yearDypl);
			}
				
		}

		this.update(rwb_search);
		changeQuertzTask(rwb_search);
	}

	@Override
	public void saveAndChangeStatus(Rwb rwb) {
		//设置任务编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		rwb.setFd_rwbh(sdf.format(new Date()));
		rwb.setFd_sjzt(Conts.ZT_Y);
		//如果是挖掘任务  填写默认值
		if(2==rwb.getFd_lx()){
			if(Conts.SJLDW_MONTH.equals(rwb.getFd_sjldw())){
				rwb.setFd_ddpl(monthDypl);
			}else{
				rwb.setFd_ddpl(yearDypl);
			}
		}
		this.save(rwb);
		changeQuertzTask(rwb);
	}

	@Override
	public List<Map<String, Object>> getCollectionTaskList(PageConfig pc,String ids,String qzrw_mc) {
		List<Map<String, Object>> list = rwbDao.getCollectionTaskList(pc,ids,qzrw_mc);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSelectedCollectTaskList(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		List<Map<String, Object>> list = rwbDao.getSelectedCollectTaskList(id);
		if(!list.isEmpty()){
			String ids = list.get(0).get("FD_QZRW_ID")==null?null:list.get(0).get("FD_QZRW_ID").toString();
			String [] ids_arr = ids.split("#");
			List<Map<String, Object>> listRwb = new ArrayList<Map<String, Object>>();
			for(String id_temp:ids_arr){
				Map<String, Object> qzMap = rwbDao.getQzrwInfo(id_temp);
				listRwb.add(qzMap);
			}
			return listRwb;
		}
		return  null;
	}
	
	
}
