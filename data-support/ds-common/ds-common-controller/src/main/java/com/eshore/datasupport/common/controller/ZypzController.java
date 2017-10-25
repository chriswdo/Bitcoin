package com.eshore.datasupport.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.pojo.Yhzy;
import com.eshore.datasupport.common.pojo.Zypz;
import com.eshore.datasupport.common.service.IYhzyService;
import com.eshore.datasupport.common.service.IZypzService;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/common")
public class ZypzController extends BaseController<Zypz> {

	@Autowired IZypzService zypzService;
	
	@Autowired IYhzyService yhzyService;

	@Override
	protected String getBasePath() {
		return "/common/zypz/";
	}

	@Override
	protected IBaseService<Zypz> getService() {
		return zypzService;
	}

	public String getModuleName() {
		return "平台管理>资源配置";
	}
	
	@RequestMapping(value="savexZypzRecord")
	@ResponseBody
	public String savexZypzRecord(Zypz zypzRecord){
		Yhb yhbSession = SessionUtil.getCurrentUserInfo(getRequest());
		zypzRecord.setFd_cjr(yhbSession.getId());
		zypzRecord.setFd_cjsj(new Date());
		this.zypzService.save(zypzRecord);
		return "success";
	}
	
	@RequestMapping(value="modifyxZypzRecord")
	@ResponseBody
	public String modifyxZypzRecord(Zypz zypzRecord){
		Zypz zypzDb = zypzService.get(zypzRecord.getId());
		zypzDb.setFd_mc(zypzRecord.getFd_mc());
		zypzDb.setFd_fjid(zypzRecord.getFd_fjid());
		zypzDb.setFd_tb(zypzRecord.getFd_tb());
		zypzDb.setFd_dz(zypzRecord.getFd_dz());
		zypzDb.setFd_xh(zypzRecord.getFd_xh());
		Yhb yhbSession = SessionUtil.getCurrentUserInfo(getRequest());
		zypzDb.setFd_gxr(yhbSession.getId());
		zypzDb.setFd_gxsj(new Date());
		zypzService.update(zypzDb);
		return "success";
	}
	
	@RequestMapping(value="deletexZypzRecord")
	@ResponseBody
	public Map<String,String> deletexZypzRecord(String idArrStr ){
		return zypzService.deleteArrayZypz(idArrStr);
	}
	
	@RequestMapping(value = "toResourceManageJSP")
	public ModelAndView  resourceManageJSP() {
		  ModelAndView mv = new ModelAndView ();
		  mv.setViewName("/common/resourceManage");
		  //存放父级菜单信息
		  List<Zypz>parentList = zypzService.getParentMenu();
		  mv.addObject("parentMenu", parentList);
		  //存放icon图片信息
		  Map<String,String>map = zypzService.getIconInfo();
		  Set<Entry<String,String>> set = map.entrySet();
		  List <Map<String,String>> iconList = new ArrayList<Map<String,String>>();
		  for(Entry<String,String> key:set){
			  if(key.getKey().startsWith("icon_")){
				  Map<String,String> iconMap =new HashMap<String,String>();
				  iconMap.put("label", key.getKey().substring(5));
				  iconMap.put("value", key.getValue());
				  iconList.add(iconMap);
			  }
		  }
		  mv.addObject("icon", iconList);
		  return mv ;
	}
	
	@RequestMapping(value = "ajaxParentMenu")
	@ResponseBody
	public Map<String,Object>  ajaxParentMenu() {
		  //存放父级菜单信息
		  List<Zypz>parentList = zypzService.getParentMenu();
		  Map<String,Object>retMap = new HashMap<String,Object>();
		  retMap.put("parentList", parentList);
		  retMap.put("result", "success");
		  return retMap ;
	}
	
	@RequestMapping(value = "ajaxMenuList")
	@ResponseBody
	public Map<String,Object>  ajaxMenuList() {
		PageConfig pc = new PageConfig();
		pc.setPageSize(10000);
		List<Zypz>list = zypzService.list(new HashMap(), pc);
		//获取排序后的列表
		List<Map<String,Object>>retList = zypzService.getSortedList(list);
		Map<String ,Object> retMap = new HashMap<String,Object>();
		retMap.put("rows", retList.toArray());
		return retMap;
	}
}
