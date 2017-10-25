package  com.eshore.datasupport.task.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.vo.RuleConfigVo;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.task.dao.IGzpzDao;
import  com.eshore.datasupport.task.pojo.Gzpz;
import  com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.datasupport.task.service.IKettleJobInfoService;
import com.eshore.datasupport.task.vo.GzpzParams;
import com.eshore.datasupport.task.vo.RjobVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;


/** 
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class GzpzServiceImpl extends BaseServiceImpl<Gzpz> implements IGzpzService {

	private final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(GzpzServiceImpl.class);
	
	
	@Autowired
	IGzpzDao gzpzDao;

	@Autowired
	IYsjbDao ysjdDao;
	@Autowired
	ISjybService sjybService;
	@Autowired
	IKettleJobInfoService  kettleJobinfo;
	
	@Override
	public IBaseDao<Gzpz> getDao() {
		return (IBaseDao<Gzpz>)gzpzDao;
	}

/*	@Override
	*//**
	 * 得到规则配置listdata
	 *//*
	public List<RuleConfigVo> getRulelistdata(PageConfig pc,int lx) {
		//通过规则类型查询规则配置表,得到规则list
		 List<Gzpz>  li=gzpzDao.getRulelistdata(pc,lx);
		 List<RuleConfigVo>  relist =new ArrayList<RuleConfigVo>();
		//将list中的ID转化为具体数据源，元数据表名称
		for (Gzpz map : li) {
			RuleConfigVo vo=new RuleConfigVo();
			vo.setFd_gzlx(map.getFd_gzlx());
			vo.setFd_gzmc(map.getFd_gzmc());
			vo.setFd_scb_id(map.getFd_scb_id());
			vo.setFd_scsjy_id(map.getFd_scsjy_id());
			vo.setFd_srb_id(map.getFd_srb_id());
			vo.setFd_srsjy_id(map.getFd_srsjy_id());
			vo.setId(map.getId());
			vo.setR_job_desc(map.getR_job_desc());
			vo.setR_job_id(map.getR_job_id());
			vo.setR_job_name(map.getR_job_name());
			List<YsjbVo>  srlist=gzpzDao.getsjyName(map.getFd_srsjy_id(),map.getFd_srb_id()); 
			List<YsjbVo>  sclist=gzpzDao.getsjyName(map.getFd_scsjy_id(),map.getFd_scb_id());
			
			map.put("srsjy", srlist.get(0).get("FD_MC"));
			map.put("srysjb", srlist.get(0).get("FD_BM"));
			map.put("scsjy", sclist.get(0).get("FD_MC"));
			map.put("scysjb", sclist.get(0).get("FD_BM"));
		}
		return relist;
	}*/

	@Override
	/**
	 * 通过规则名称模糊查询
	 * @param pc 分页
	 * @param gzm 规则名称
	 * @param lx  规则类型
	 * @return
	 */	
	public List<RuleConfigVo> getRulelistdataBygzmc(PageConfig pc,String gzm,int lx) {
		List<Gzpz>  li=gzpzDao.getRulelistdataBygzmc(pc,gzm,lx);
		List<RuleConfigVo>  relist =new ArrayList<RuleConfigVo>();
		//将list中的ID转化为具体数据源，元数据表名称
		for (Gzpz map : li) {
			RuleConfigVo ruleVo=new RuleConfigVo();
			ruleVo.setFd_gzlx(map.getFd_gzlx());
			ruleVo.setFd_gzmc(map.getFd_gzmc());
			ruleVo.setFd_scb_id(map.getFd_scb_id());
			ruleVo.setFd_scsjy_id(map.getFd_scsjy_id());
			ruleVo.setFd_srb_id(map.getFd_srb_id());
			ruleVo.setFd_srsjy_id(map.getFd_srsjy_id());
			ruleVo.setId(map.getId());
			ruleVo.setR_job_desc(map.getR_job_desc());
			ruleVo.setR_job_id(map.getR_job_id());
			ruleVo.setR_job_name(map.getR_job_name());
			Sjyb srobj=sjybService.get(map.getFd_srsjy_id());
			Sjyb scobj=sjybService.get(map.getFd_scsjy_id());
			ruleVo.setFd_srsjy_name(srobj.getFd_mc());
			ruleVo.setFd_scsjy_name(scobj.getFd_mc());
/*			List<YsjbVo>  srlist=gzpzDao.getsjyName(map.getFd_srsjy_id(),null==map.getFd_srb_id()?" ":map.getFd_srb_id()); 
			List<YsjbVo>  sclist=gzpzDao.getsjyName(map.getFd_scsjy_id(),null==map.getFd_scb_id()?" ":map.getFd_scb_id());
			ruleVo.setFd_scb_name(sclist.get(0).getFd_bm());
			ruleVo.setFd_scsjy_name(sclist.get(0).getFd_mc());
			ruleVo.setFd_srb_name(srlist.get(0).getFd_bm());
			ruleVo.setFd_srsjy_name(srlist.get(0).getFd_mc());*/
			relist.add(ruleVo);
		}
		return relist;
	}
	
	/**
	 * 查询数据源表
	 */
/*	public List<Map<String, Object>> getDatainpage() {
		List<Map<String, Object>>  res=gzpzDao.getDatainpage();
		return res;
	}*/
	@Override
	/**
	 * 根据数据源ID查询元数据表
	 * @param ids  数据源ID
	 * @return
	 */
	public List<Ysjb> getSrbiao(String ids) {
		PageConfig pg=new PageConfig();
		pg.setPageSize(Integer.MAX_VALUE);
		List<Ysjb>  res=ysjdDao.getmetedataTable(ids, pg);
		return res;
	}

	@Override
	/**
	 * 查询采集的job
	 * @param pc
	 * @return
	 */
	public List<RjobVo> getJoblist(PageConfig pc,String jobtxt,String sryid,String scyid) {
		List<RjobVo> res=new ArrayList<RjobVo>();
		java.util.Set<String> retSet = new HashSet<String>();
		StringBuilder sbf=new StringBuilder();
		String strsql="";
		sbf.append(" and j.ID_JOB in (");
		if(StringUtils.isNotBlank(sryid) || StringUtils.isNotBlank(scyid)){
			  retSet=  kettleJobinfo.getKettleJobId(sryid, scyid);
		   if(!retSet.isEmpty()){
				  for (String str : retSet) {
					  sbf.append(str).append(",");
				}
				  sbf.toString().substring(0, sbf.toString().length()-1);
				  strsql= sbf.toString().substring(0, sbf.toString().length()-1)+")";
				  res=gzpzDao.getJoblist(pc,jobtxt,strsql); 
		   }
		}
		
		return res;
	}


	@Override
	/**
	 * 查询处理的job
	 * @param pc
	 * @return
	 */
	public List<RjobVo> getclJoblist(PageConfig pc,String jobtxt,String sryid,String scyid) {
		List<RjobVo> res=new ArrayList<RjobVo>();
		java.util.Set<String> retSet = new HashSet<String>();
		StringBuilder sbf=new StringBuilder();
		String strsql="";
		sbf.append(" and j.ID_JOB in (");
		if(StringUtils.isNotBlank(sryid) || StringUtils.isNotBlank(scyid)){
			  retSet=  kettleJobinfo.getKettleJobId(sryid, scyid);
		   if(!retSet.isEmpty()){
				  for (String str : retSet) {
					  sbf.append(str).append(",");
				}
				  sbf.toString().substring(0, sbf.toString().length()-1);
				  strsql= sbf.toString().substring(0, sbf.toString().length()-1)+")";
				  res=gzpzDao.getclJoblist(pc,jobtxt,strsql);
		   }
		}
		
		return res;
	}
	
	@Override
	/**
	 * 查询调度挖掘的job
	 * @param pc
	 * @return
	 */
	public List<RjobVo> getddJoblist(PageConfig pc,String jobtxt,String sryid,String scyid) {
		List<RjobVo> res=new ArrayList<RjobVo>();
		java.util.Set<String> retSet = new HashSet<String>();
		StringBuilder sbf=new StringBuilder();
		String strsql="";
		sbf.append(" and j.ID_JOB in (");
		if(StringUtils.isNotBlank(sryid) || StringUtils.isNotBlank(scyid)){
			  retSet=  kettleJobinfo.getKettleJobId(sryid, scyid);
		   if(!retSet.isEmpty()){
				  for (String str : retSet) {
					  sbf.append(str).append(",");
				}
				  sbf.toString().substring(0, sbf.toString().length()-1);
				  strsql= sbf.toString().substring(0, sbf.toString().length()-1)+")";
				  res=gzpzDao.getddJoblist(pc,jobtxt,strsql);
		   }
		}
		
		return res;
	}
	
	@Override
	public List<Map<String,Object>> findGzpzList(Map<String,Object>params, PageConfig pc) {
		List<Map<String,Object>>list = this.gzpzDao.findGzpzList(params, pc);
		return list;
	}

	@Override
	public List<Map<String, Object>> findRwbList(Map<String,Object>params, PageConfig pc) {
		List<Map<String,Object>>list = this.gzpzDao.findRwbList(params, pc);
		//转换时间参数
		for(Map<String,Object> m : list){
			m.put("FD_GXSJ",m.get("FD_GXSJ")==null?"":ymdhms.format((Date)m.get("FD_GXSJ")));
		}
		return list;
	}

	
	@Override
	/**
	 * 保存采集规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	public String  getgzlistBy(GzpzParams params) {
		String res="ok";
		try {
			String rulename=params.getRulename();
			Integer lx=0;
			//排除添加的重复规则
			List<Gzpz> list = gzpzDao.getgzlistBy(rulename,lx);
			if(!list.isEmpty()){
				res="repeat";
				return res;
			}
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=new Gzpz();
			Date  da=new Date();
			String uuid=UUID.randomUUID().toString();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(0);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setId(uuid);
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.save(gz);	
		} catch (Exception e) {
			logger.info(e);
			res="fail";
		}
		
		return res;
	}
	@Override
	/**
	 * 保存挖掘规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	public String  saveddRuledata(GzpzParams params){
		String res="ok";
		try {
			String rulename=params.getRulename();
			//排除添加的重复规则
			Integer lx=2;
			List<Gzpz> list = gzpzDao.getgzlistBy(rulename,lx);
			if(!list.isEmpty()){
				res="repeat";
				return res;
			}
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=new Gzpz();
			Date  da=new Date();
			String uuid=UUID.randomUUID().toString();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(2);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setId(uuid);
			gz.setR_job_desc("");
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.save(gz);
		} catch (Exception e) {
            logger.info(e);
			res="fail";
		}
		return res;	
	}
	

	@Override
	/**
	 * 保存处理规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	public String saveclRuledata(GzpzParams params) {
		String res="ok";
		try {
			String rulename=params.getRulename();
			//排除添加的重复规则
			int lx=1;
			List<Gzpz> list = gzpzDao.getgzlistBy(rulename,lx);
			if(!list.isEmpty()){
				res="repeat";
				return res;
			}
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=new Gzpz();
			Date  da=new Date();
			String uuid=UUID.randomUUID().toString();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(1);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setId(uuid);
			gz.setR_job_desc("");
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.save(gz);
		} catch (Exception e) {
	        logger.info(e);
			res="fail";
		}
		return res;
	}

	@Override
	/**
	 * 更新规则配置表的采集规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	public String updateRuledata(GzpzParams params, String gzid) {
		String res="ok";
		try {
			String rulename=params.getRulename();
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=this.get(gzid);
			Date  da=new Date();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(0);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setR_job_desc("");
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.update(gz);
		} catch (Exception e) {
            logger.info(e);
			res="fail";
		}
		return res;
	}

	@Override
	/**
	 * 更新规则配置表的处理规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	public String updateclRuledata(GzpzParams params, String gzid) {
		
		String res="ok";
		try {
			String rulename=params.getRulename();
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=this.get(gzid);
			Date  da=new Date();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(1);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setR_job_desc("");
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.update(gz);
		} catch (Exception e) {
            logger.info(e);
			res="fail";
		}		
		return res;
	}

	@Override
	/**
	 * 更新规则配置表的调度规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	public String updateddRuledata(GzpzParams params, String gzid) {
		String res="ok";
		try {
			String rulename=params.getRulename();
			String  scyid=params.getScyid();
			String  scbid=params.getScbid();
			String  sryid=params.getSryid();
			String  srbid=params.getSrbid();
			Integer jobid=params.getJobid();
			String jobname=params.getJobname();
			String  jobdesc=params.getJobdesc();
			Gzpz gz=this.get(gzid);
			Date  da=new Date();
			gz.setFd_gxr("");
			gz.setFd_gxsj(da);
			gz.setFd_gzlx(2);
			gz.setFd_gzmc(rulename);
			gz.setFd_scb_id(scbid);
			gz.setFd_scsjy_id(scyid);
			gz.setFd_sjzt("Y");
			gz.setFd_srb_id(srbid);
			gz.setFd_srsjy_id(sryid);
			gz.setR_job_desc("");
			gz.setR_job_id(jobid);
			gz.setR_job_name(jobname);
			gz.setR_job_desc(jobdesc);
			this.update(gz);
		} catch (Exception e) {
            logger.info(e);
			res="fail";
		}		
		return res;
	}
}
 