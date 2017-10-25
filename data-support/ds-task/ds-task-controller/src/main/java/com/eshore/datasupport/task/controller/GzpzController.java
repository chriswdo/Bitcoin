package com.eshore.datasupport.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.vo.RuleConfigVo;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.task.pojo.Gzpz;
import com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.datasupport.task.vo.GzpzParams;
import com.eshore.datasupport.task.vo.RjobVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rulemacontroller")
public class GzpzController extends BaseController<Gzpz> {
    private static final Logger logger = Logger.getLogger(GzpzController.class);
	
	@Autowired IGzpzService gzpzService;
	@Autowired ISjybService sjybService;
	@Override
	protected String getBasePath() {
		return "/datasupport";
	}

	@Override
	protected IBaseService<Gzpz> getService() {
		return gzpzService;
	}

	public String getModuleName() {
		return "任务调度管理>规则配置";
	}

	
/*	@RequestMapping(value = "getRulelistdata")
	@ResponseBody
	*//**
	 * 通过类型查询表的对应数据
	 * @param pc 分页
	 * @param lx 规则类型
	 * @return
	 *//*
	public Map<String,Object> getRulelistdata(PageConfig pc,int lx) {
		List<Map<String, Object>> telist = gzpzService.getRulelistdata(pc,lx);
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		retMap.put("total",pc.getRowCount());	
		retMap.put("list", telist);
		return retMap;

	}*/

	@RequestMapping(value = "ajaxRulelistdataBygzmc")
	@ResponseBody
	/**
	 * 查询规则配置表
	 * @param pc  分页
	 * @param opt 规则名称
	 * @param lx  类型
	 * @return
	 */
	public Map<String,Object> ajaxRulelistdataBygzmc(PageConfig pc,String opt,int lx) {
		String optt = "";   
		if(opt!=null && !opt.isEmpty()){
			optt=opt;
		   }
		String gzm = optt.trim();
		List<RuleConfigVo> telist = gzpzService.getRulelistdataBygzmc(pc,gzm,lx);
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		retMap.put("total",pc.getRowCount());	
		retMap.put("list", telist);
		return retMap;

	}
	
	  @RequestMapping(value="getcjruleDatapage")
	  //跳转到采集页面
	  public ModelAndView getcjruleDatapage(){
		  ModelAndView mv = new ModelAndView ();
		 // List<Map<String, Object>>  telist=gzpzService.getDatainpage();	
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  telist=sjybService.list(new Sjyb(), pg);
		  mv.addObject("selist", telist);
		  mv.setViewName(getBasePath() + "/rulelist");
		  logger.info(getBasePath() + "/rulelist");
		  return mv ;	  		  
	  } 
	  
	  @RequestMapping(value="getclruleDatapage")
	//跳转到处理页面
	  public ModelAndView getclruleDatapage(){
		  ModelAndView mv = new ModelAndView ();
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  telist=sjybService.list(new Sjyb(), pg);
		  mv.addObject("selist", telist);
		  mv.setViewName(getBasePath() + "/clrulelist");
		  logger.info(getBasePath() + "/clrulelist");
		  return mv ;	  		  
	  } 
	  
	  @RequestMapping(value="getddruleDatapage")
	//跳转到调度页面
	  public ModelAndView getddruleDatapage(){
		  ModelAndView mv = new ModelAndView ();
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  telist=sjybService.list(new Sjyb(), pg);	
		  mv.addObject("selist", telist);
		  mv.setViewName(getBasePath() + "/ddrulelist");
		  logger.info(getBasePath() + "/ddrulelist");
		  return mv ;	  		  
	  } 

/*	//采集规则编辑
	@RequestMapping(value = "editRuledata")
	@ResponseBody
	public Map <String,Object> editRuledata(String jobname) {
		 Map <String,Object>  retMap = new HashMap<String ,Object>();
		  List<Map<String, Object>>  relist=gzpzService.getRuledataByid(jobname);	
		  retMap.put("relist", relist);
		  return retMap ;
	}*/
	

	
	
	
	
	@RequestMapping(value = "getSrbiao")
	@ResponseBody
	public Map<String,Object> getSrbiao(String opt,PageConfig  pc) {
		List<Ysjb> telist = gzpzService.getSrbiao(opt);
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		retMap.put("list", telist);
		retMap.put("total",pc.getRowCount());	
		return retMap;

	}
	
	
	
	@RequestMapping(value = "getJoblist")
	@ResponseBody
	/**
	 * 得到采集规则的job list
	 * @param pc
	 * @return
	 */
	public Map<String,Object> getJoblist(PageConfig  pc,String opt,String sryid,String scyid) {
		String jobtxt = "";   
		if(opt!=null && !opt.isEmpty()){
			jobtxt=opt;
		   }
		List<RjobVo>  joblist=gzpzService.getJoblist(pc,jobtxt.trim(),sryid,scyid);		
		Map <String,Object>  retMap = new HashMap<>();
		retMap.put("list", joblist);
		retMap.put("total",pc.getRowCount());	
		return retMap;
	}
	
	@RequestMapping(value = "getclJoblist")
	@ResponseBody
	/**
	 * 得到处理规则的job list
	 * @param pc
	 * @return
	 */
	public Map<String,Object> getclJoblist(PageConfig  pc,String opt,String sryid,String scyid) {
		String jobtxt = "";   
		if(opt!=null && !opt.isEmpty()){
			jobtxt=opt;
		   }
		List<RjobVo>  joblist=gzpzService.getclJoblist(pc,jobtxt,sryid,scyid);		
		Map <String,Object>  retMap = new HashMap<>();
		retMap.put("list", joblist);
		retMap.put("total",pc.getRowCount());	
		return retMap;
	}
	
	@RequestMapping(value = "getddJoblist")
	@ResponseBody
	/**
	 * 得到调度规则的job list
	 * @param pc
	 * @return
	 */
	public Map<String,Object> getddJoblist(PageConfig  pc,String opt,String sryid,String scyid) {
		String jobtxt = "";   
		if(opt!=null && !opt.isEmpty()){
			jobtxt=opt;
		   }
		List<RjobVo>  joblist=gzpzService.getddJoblist(pc,jobtxt,sryid,scyid);		
		Map <String,Object>  retMap = new HashMap<>();
		retMap.put("list", joblist);
		retMap.put("total",pc.getRowCount());	
		return retMap;
	}
	//保存采集规则
	@RequestMapping(value = "saveRuledata")
	@ResponseBody
	public String saveRuledata(GzpzParams params) {
		String  res=gzpzService.getgzlistBy(params);			
		return res;
	}
	//保存调度规则
	@RequestMapping(value = "saveddRuledata")
	@ResponseBody
	public String saveddRuledata(GzpzParams params) {
		String res=gzpzService.saveddRuledata(params);	
		return res;
	}
	
	//保存处理规则
	@RequestMapping(value = "saveclRuledata")
	@ResponseBody
	public String saveclRuledata(GzpzParams params) {
		String res=gzpzService.saveclRuledata(params);	
		return res;
	}
	
	
	//更新采集规则
	@RequestMapping(value = "updateRuledata")
	@ResponseBody
	public String updateRuledata(GzpzParams params,String gzid) {	
		String res=gzpzService.updateRuledata(params,gzid);	
		return res;
	}
	//更新处理规则
	@RequestMapping(value = "updateclRuledata")
	@ResponseBody
	public String updateclRuledata(GzpzParams params,String gzid) {	
		String res=gzpzService.updateclRuledata(params,gzid);	
		return res;
	}
	
	//更新调度规则
	@RequestMapping(value = "updateddRuledata")
	@ResponseBody
	public String updateddRuledata(GzpzParams params,String gzid) {	
		String res=gzpzService.updateddRuledata(params,gzid);	
		return res;		
	}	
	
	@RequestMapping(value="getTaskScheduleJSP")
	public ModelAndView getTaskScheduleJSP(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getBasePath()+"/taskScheduleManage");
		return mav;
	}
	
	@RequestMapping(value="ajaxGzpzRecord")
	@ResponseBody
	public Map<String,Object> ajaxGzpzRecord(String fd_gzmc,String tb_name,String fd_lx,PageConfig pc){
		Map<String,Object>params = new HashMap<>();
		params.put("fd_gzmc", fd_gzmc);
		params.put("tb_name", tb_name);
		params.put("fd_lx", fd_lx);
		List<Map<String,Object>>list = gzpzService.findGzpzList(params, pc);
		Map <String,Object>retMap = new HashMap<>();
		retMap.put("list", list);
		retMap.put("total",pc.getRowCount());
		return retMap;
	}
	
	@RequestMapping(value="ajaxRwbRecord")
	@ResponseBody
	public Map<String,Object> ajaxRwbRecord(String fd_gzmc,String db_name,String fd_lx,PageConfig pc){
		Map<String,Object>params = new HashMap<>();
		params.put("fd_gzmc", fd_gzmc);
		params.put("db_name", db_name);
		params.put("fd_lx", fd_lx);
		List<Map<String,Object>>list = gzpzService.findRwbList(params, pc);
		Map <String,Object>retMap = new HashMap<>();
		retMap.put("list", list);
		retMap.put("total",pc.getRowCount());
		return retMap;
	}
}
