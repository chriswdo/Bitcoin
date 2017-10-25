package com.eshore.datasupport.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.pojo.Yhzy;
import com.eshore.datasupport.common.service.IYhzyService;
import com.eshore.datasupport.common.service.IZypzService;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/userMenu")
public class YhzyController extends BaseController<Yhzy> {

	@Autowired IYhzyService yhzyService;
	
	@Autowired IZypzService zypzService;

	@Override
	protected String getBasePath() {
		return "/common/yhzy/";
	}

	@Override
	protected IBaseService<Yhzy> getService() {
		return yhzyService;
	}

	public String getModuleName() {
		return "用户管理>用户资源关系";
	}
	
	@RequestMapping(value = "listxMenuById")
	@ResponseBody
	public  List listxMenuById(String id) {
		Map<String,String>map = zypzService.getIconInfo();
		String basePath =  map.get("iconBasepath");
		String contextPath = this.getRequest().getContextPath();
		List list = yhzyService.listxMenuById(id,contextPath+basePath);
		return list;
	}
	
	@RequestMapping(value = "ajaxMenuListById")
	@ResponseBody
	public List  ajaxMenuListById(String id) {
		if(!StringUtils.isNotBlank(id)){
			List list_null = new ArrayList();
			return list_null;
		}
		Map<String,String>map = zypzService.getIconInfo();
		String basePath =  map.get("iconBasepath");
		String contextPath = this.getRequest().getContextPath();
		List list = yhzyService.ajaxZtreeMenuListById(id,contextPath+basePath);
		return list;
	}
	
	
}
