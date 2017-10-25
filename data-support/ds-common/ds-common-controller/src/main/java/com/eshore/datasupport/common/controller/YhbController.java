package com.eshore.datasupport.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.pojo.Zypz;
import com.eshore.datasupport.common.service.IYhbService;
import com.eshore.datasupport.common.service.IZypzService;
import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/userManage")
public class YhbController extends BaseController<Yhb> {

	@Autowired IYhbService yhbService;
	
	@Autowired IZypzService zypzService;
	
	@Override
	protected String getBasePath() {
		return "/common/yhb/";
	}

	@Override
	protected IBaseService<Yhb> getService() {
		return yhbService;
	}

	public String getModuleName() {
		return "平台管理>用户表";
	}
	
	@RequestMapping(value = "toUserManageJSP")
	public ModelAndView  resourceManageJSP() {
		  ModelAndView mv = new ModelAndView ();
		  Map<String,String>map = zypzService.getIconInfo();
		  mv.addObject("iconBasepath", map.get("iconBasepath"));
		  mv.setViewName("/common/userManage");
		  return mv ;
	}
	
	@RequestMapping(value = "ajaxYhbRecord")
	@ResponseBody
	public Map<String,Object>  ajaxYhbRecord(String fd_yhmc,String fd_zt,PageConfig pc) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("fd_yhmc", fd_yhmc);
		  params.put("fd_zt", fd_zt);
		  List<Yhb> list = yhbService.listByYhmc(params,pc);
		  Map <String,Object>retMap = new HashMap<String ,Object>();
		  retMap.put("list", list);
		  retMap.put("total",pc.getRowCount());
		  return retMap ;
	}
	
	@RequestMapping(value = "savexYhbRecord")
	@ResponseBody
	public  String savexYhbRecord(Yhb yhb,String nodes) {
		if("admin".equals(yhb.getFd_dlmc()) || yhbService.Fd_dlmcIsExist(yhb.getFd_dlmc())){
			return "failed";
		}
		Yhb yhbSession = SessionUtil.getCurrentUserInfo(getRequest());
		yhb.setFd_cjr(yhbSession.getId());
		yhb.setFd_cjsj(new Date());
		yhbService.savexYhbRecord(yhb,nodes);
		return "success";
	}
	
	@RequestMapping(value = "initPassword")
	@ResponseBody
	public  String initPassword(Yhb yhb) {
		Yhb yhbDb = yhbService.get(yhb.getId());
		yhbDb.setFd_mm(yhbService.getMd5Password(yhbService.getMd5Password("123456")));
		yhbService.update(yhbDb);
		return "success";
	}
	
	@RequestMapping(value = "modifyxYhbRecord")
	@ResponseBody
	public  String modifyxSjybRecord(Yhb yhb,String nodes) {
		//更新人  更新时间
		Yhb yhbSession = SessionUtil.getCurrentUserInfo(getRequest());
		yhb.setFd_gxr(yhbSession.getId());
		yhb.setFd_gxsj(new Date());
		//保存modify
		yhbService.modifyxSjybRecord(yhb,nodes);
		return "success";
	}
	
	@RequestMapping(value = "modifyxPassword")
	@ResponseBody
	public  String modifyxPassword(String username,String password_old,String password_new) {
		Yhb yhbDb = yhbService.getYhbByFd_dlmc(username);
		String encryStr = yhbService.getMd5Password(password_old);
		if(encryStr.equals(yhbDb.getFd_mm())){
			Yhb yhbSession = SessionUtil.getCurrentUserInfo(getRequest());
			yhbDb.setFd_gxr(yhbSession.getId());
			yhbDb.setFd_gxsj(new Date());
			yhbDb.setFd_mm(yhbService.getMd5Password(password_new));
			yhbService.update(yhbDb);
			return "success";
		}else{
			return "passwordErr";
		}
	}
	
	@RequestMapping(value = "login")
	@ResponseBody
	public  String login(Yhb yhb) {
		Yhb yhbDb = yhbService.getYhbByFd_dlmc(yhb.getFd_dlmc());
		//如果用户不存
		if(yhbDb==null){
			if("admin".equals(yhb.getFd_dlmc()) ){
				return "adminErr";
			}else{
				return "unameErr";
			}
		}
		//如果用户存在
		String encryStr = yhbService.getMd5Password(yhb.getFd_mm());
		if(Conts.ZT_Y.equals(yhbDb.getFd_zt())){
			if(encryStr.equals(yhbDb.getFd_mm())){
				HttpSession session = this.getRequest().getSession();
				session.setAttribute(Conts.SESSIONFLAG, yhbDb);
				session.setMaxInactiveInterval(30*60);
				return "success";
			}
			return "pwErr";
		}
		return "failed";
	}
	
	@RequestMapping(value = "toindexJsp")
	public  String toindexJsp() {
		HttpSession session = this.getRequest().getSession(false);
		if(session !=null){
			return  "redirect:../index.jsp";
		}else{
			return "redirect:../login.jsp";
		}
	}
	
	@RequestMapping(value = "ajaxNodes")
	@ResponseBody
	public List<Map<String,Object>> ajaxNodes(){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
		if(yhb==null){
			return nodes;
		}
		List<Map<String,Object>> retlist = null;
		if("admin".equals(yhb.getFd_dlmc())){
			retlist = getAdminNodes();
		}else{
			retlist = getNormalNodes(yhb);
		}
		yhbService.getNodes(retlist,nodes);
		return nodes;
	}

	private List<Map<String,Object>> getAdminNodes() {
		PageConfig pc = new PageConfig();
		pc.setPageSize(10000);
		List<Zypz>list = zypzService.list(new HashMap(),pc);
		List<Map<String,Object>>retlist =zypzService.getSortedList(list);
		return retlist;
	}
	
	private List<Map<String,Object>>  getNormalNodes(Yhb yhb){
		List<Zypz>list = zypzService.ajaxMenuListById(yhb.getId());
		List<Map<String,Object>>retlist = zypzService.getSortedList(list);
		return retlist;
	}
	
	@RequestMapping(value = "toHomeJsp")
	public  ModelAndView toHomeJsp() {
		ModelAndView mv = new ModelAndView ();
		mv.setViewName("../home");
		return  mv;
	}
	
}
