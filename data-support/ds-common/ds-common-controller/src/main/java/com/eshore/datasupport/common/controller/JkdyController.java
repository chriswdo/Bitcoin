package com.eshore.datasupport.common.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.service.IJkdyService;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.JkdyVo;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/interfaceDefine")
public class JkdyController extends BaseController<Jkdy> {

	@Autowired IJkdyService jkdyService;
	
	@Autowired IGzpzService gzpzService;
	
	@Autowired ISjybService sjybService;
	
	@Override
	protected String getBasePath() {
		return "/common";
	}

	@Override
	protected IBaseService<Jkdy> getService() {
		return jkdyService;
	}

	public String getModuleName() {
		return "接口服务";
	}
	
	
	
	@RequestMapping(value = "getInterfaceMeg")
	@ResponseBody
	public Map<String,Object>  getInterfaceMeg(String jkname,PageConfig pc) {
		List<JkdyVo> list = jkdyService.getInterfaceMeg(jkname,pc);
		  Map <String,Object>retMap = new HashMap<String ,Object>();
		  retMap.put("list", list);
		  retMap.put("total",pc.getRowCount());
		  return retMap ;
	}
	
	
	@RequestMapping(value = "toInterfaceJsp")
	public ModelAndView  toAlarmManageJSP() {
		ModelAndView mv = new ModelAndView ();
		PageConfig pc=new PageConfig();
		pc.setPageSize(Integer.MAX_VALUE);
		List<Sjyb>  telist=sjybService.list(new Sjyb(), pc);
		 mv.addObject("selist", telist);
		mv.setViewName(getBasePath() +"/interfaceMeg");
		return mv ;
	}
	
	@RequestMapping(value = "savexJkMessage")
	@ResponseBody
	public String  savexJkMessage(Jkdy jk,HttpServletRequest request) {
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        String res= jkdyService.savexJkMessage(jk,userid);
		return res ;
	}
	
	@RequestMapping(value = "updateJkMessage")
	@ResponseBody
	public String  updateJkMessage(Jkdy jk,HttpServletRequest request) {
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        String res= jkdyService.updateJkMessage(jk,userid);
		return res ;
	}
	
	
	/*
	 * 首页告警信息展示
	 */
	@RequestMapping(value = "changeInterdaceStatus")
	@ResponseBody
	public String  changeInterdaceStatus(String id,String status) {
         String res=jkdyService.changeInterdaceStatus(id, status);
		return res ;
	}
}
