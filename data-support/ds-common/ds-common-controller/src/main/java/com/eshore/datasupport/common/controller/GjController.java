package com.eshore.datasupport.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Gj;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.service.IGjService;
import com.eshore.datasupport.common.service.IGjsjService;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/alarm")
public class GjController extends BaseController<Gj> {

	@Autowired IGjService gjService;
	
	@Autowired IGzpzService gzpzService;
	
	@Autowired IGjsjService gjsjService;
	
	@Override
	protected String getBasePath() {
		return "/common/gj/";
	}

	@Override
	protected IBaseService<Gj> getService() {
		return gjService;
	}

	public String getModuleName() {
		return "告警";
	}
	
	
	
	@RequestMapping(value = "listxAlarmRecord")
	@ResponseBody
	public Map<String,Object>  listxAlarmRecord(String fd_yhmc,String fd_gzmc,String fd_gjfs ,PageConfig pc) {
		  Map <String,String> params = new HashMap<String,String>();
		  params.put("fd_yhmc", fd_yhmc);
		  params.put("fd_gzmc", fd_gzmc);
		  params.put("fd_gjfs", fd_gjfs);
		  List<Gj> list = gjService.listxAlarmRecord(params,pc);
		  Map <String,Object>retMap = new HashMap<String ,Object>();
		  retMap.put("list", list);
		  retMap.put("total",pc.getRowCount());
		  return retMap ;
	}
	
	@RequestMapping(value="ajaxTaskInfo")
	@ResponseBody
	public Map<String,Object> ajaxTaskInfo(String fd_gzmc,String db_name,PageConfig pc){
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("fd_gzmc", fd_gzmc);
		params.put("db_name", db_name);
		List<Map<String,Object>>list = gjService.ajaxTaskInfo(params, pc);
		Map <String,Object>retMap = new HashMap<String ,Object>();
		retMap.put("list", list);
		retMap.put("total",pc.getRowCount());
		return retMap;
	}
	
	@RequestMapping(value = "toAlarmManageJSP")
	public ModelAndView  toAlarmManageJSP() {
		ModelAndView mv = new ModelAndView ();
		mv.setViewName("/common/alarmManage");
		return mv ;
	}
	
	@RequestMapping(value = "savexGjRecord")
	@ResponseBody
	public String  savexGjRecord(Gj gjRecord) {
		Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
		gjRecord.setFd_cjr(yhb.getId());
		gjRecord.setFd_cjsj(new Date());
		gjService.save(gjRecord);  
		return "success" ;
	}
	
	@RequestMapping(value = "modifyxGjRecord")
	@ResponseBody
	public String  modifyxGjRecord(Gj gjRecord) {
		Gj gj = gjService.get(gjRecord.getId());
		gj.setFd_gjfs(gjRecord.getFd_gjfs());
		gj.setFd_rwid(gjRecord.getFd_rwid());
		gj.setFd_yh_id(gjRecord.getFd_yh_id());
		gj.setFd_zt(gjRecord.getFd_zt());
		//获取当前用户信息
		Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
		gj.setFd_gxr(yhb.getId());
		gj.setFd_gxsj(new Date());
		gjService.update(gj);  
		return "success" ;
	}
	
	
	/*
	 * 首页告警信息展示
	 */
	@RequestMapping(value = "ajaxHomeGjInfo")
	@ResponseBody
	public Map<String,Object>  ajaxHomeGjInfo(PageConfig pc) {
		List<Map<String,String>> retList = gjsjService.ajaxHomeGjInfo(pc);
		Map <String,Object>retMap = new HashMap<String ,Object>();
		retMap.put("list", retList);
		retMap.put("total",pc.getRowCount());	
		return retMap ;
	}
}
