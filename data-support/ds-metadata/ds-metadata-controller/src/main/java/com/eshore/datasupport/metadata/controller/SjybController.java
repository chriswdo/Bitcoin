package com.eshore.datasupport.metadata.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.metadata.service.IYsjbService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/ysjgl")
public class SjybController extends BaseController<Sjyb> {

	@Autowired ISjybService sjybService;
	
	@Autowired IYsjbService ysjbService;
	
	@Override
	protected String getBasePath() {
		return "/metadata";
	}

	@Override
	protected IBaseService<Sjyb> getService() {
		return sjybService;
	}

	public String getModuleName() {
		return "元数据管理>数据源表";
	}
	
	@RequestMapping(value="listSjybRecord")
	public ModelAndView listSjybRecord(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getBasePath()+"/dataSourceManage");
		return mav;
	}
	
	@RequestMapping(value="ajaxSjybRecord")
	@ResponseBody
	public Map<String,Object> ajaxSjybRecord(String fd_mc,String fd_lx ,PageConfig pc){
		Map<String,String>params = new HashMap<String,String>();
		params.put("fd_mc", fd_mc);
		params.put("fd_lx", fd_lx);
		List<Sjyb>list = sjybService.listSjybRecord(params,pc );
		Map <String,Object>retMap = new HashMap<String ,Object>();
		retMap.put("list", list);
		retMap.put("total",pc.getRowCount());
		return retMap;
	}
	
	@RequestMapping(value="addSjybRecord")
	@ResponseBody
	public String addSjybRecord(Sjyb sjybRecord){
		Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
		sjybRecord.setFd_cjr(yhb.getId());
		sjybRecord.setFd_cjsj(new Date());
		sjybRecord.setId(null);
		String str = sjybService.saveSjybRecord(sjybRecord);
		return str;
	}
	
	@RequestMapping(value="updateSjybRecord")
	@ResponseBody
	public String updateSjybRecord(Sjyb sjybRecord){
		sjybService.updateSjybRecord(sjybRecord);
		return "success";
	}
	
	@RequestMapping(value="deleteSjybRecord")
	@ResponseBody
	public String deleteSjybRecord(Sjyb sjybRecord){
		//判断元数据表中是否存在该数据源的信息
		if(ysjbService.findAnyBysjyId(sjybRecord.getId())){
			return "1";
		}
		sjybService.deleteSjybAndR_databaseInfo(sjybRecord.getId());
		return "success";
	}
	
	@RequestMapping(value="checkConnecting")
	@ResponseBody
	public String checkConnecting(Sjyb sjybRecord){
		return sjybService.checkConnecting(sjybRecord);
	}
	
}
