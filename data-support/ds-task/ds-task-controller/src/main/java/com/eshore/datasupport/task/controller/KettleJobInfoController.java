package com.eshore.datasupport.task.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.datasupport.task.service.IKettleJobInfoService;
import com.eshore.datasupport.task.service.IRwslService;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/kettleJob")
public class KettleJobInfoController extends BaseController{

	@Autowired IKettleJobInfoService kettleJobInfoService;
	
	@Autowired
	IRwslService rwslService;
	
	@Override
	protected String getBasePath() {
		return null;
	}

	@Override
	protected IBaseService getService() {
		return null;
	}
	
	/**
	 * 获取页面点击kettlejob详情时显示的信息
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "ajaxKettleJobInfo")
	@ResponseBody
	public Map<String,Object> ajaxKettleJobInfo(String jobId){
		Map<String,Object>retMap = new HashMap<String,Object>();
		retMap.put("input", kettleJobInfoService.getInputInfo(jobId));
		retMap.put("output", kettleJobInfoService.getOutputInfo(jobId));
		retMap.put("valid", kettleJobInfoService.getValidInfo(jobId));
		retMap.put("chart", kettleJobInfoService.getChartInfo(jobId));
		return retMap;
	}
	
	@RequestMapping(value = "test")
	@ResponseBody
	public void test() throws UnsupportedEncodingException{
		Rwsl rwsl  = new Rwsl();
		rwsl.setFd_bzxx("测试数据".getBytes("UTF-8"));
		for(byte b:rwsl.getFd_bzxx()){
			System.out.println(b);
		}
		rwslService.save(rwsl);
	}
	
	@RequestMapping(value = "test1")
	@ResponseBody
	public String test1(String id) throws UnsupportedEncodingException{
		Rwsl rwsl = rwslService.get(id);
		String str = new String(rwsl.getFd_bzxx(),"UTF-8");
		return str;
	}
	
}
