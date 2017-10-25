package com.eshore.datasupport.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.task.pojo.Gzpz;
import com.eshore.datasupport.task.pojo.Rwb;
import com.eshore.datasupport.task.service.IGzpzService;
import com.eshore.datasupport.task.service.IRwbService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/taskRwb")
public class RwbController extends BaseController<Rwb> {
	Logger logger = LoggerFactory.getLogger(GzpzController.class);

	@Autowired IRwbService rwbService;
	
	@Autowired IGzpzService gzpzService;
	
	@Override
	protected String getBasePath() {
		return "/taskRwb";
	}

	@Override
	protected IBaseService<Rwb> getService() {
		return rwbService;
	}

	public String getModuleName() {
		return "任务调度管理>任务表";
	}
	
	@RequestMapping(value="checkfd_ddsj")
	@ResponseBody
	public boolean ajaxCheckFd_ddpl(String fd_ddpl){
		if(fd_ddpl==null)return false;
		return org.quartz.CronExpression.isValidExpression(fd_ddpl);
	}
	
	/**
	 * 添加或更新
	 * @param fd_scqdsj_n
	 * @param fd_sjsjc_n
	 * @param rwb 如果rwb的id不为null,表示该操作是跟新操作
	 * @return
	 */
	@RequestMapping(value="addRwbRecord")
	@ResponseBody
	public String addOrUpdateRwbRecord(String fd_scqdsj_n,String fd_sjsjc_n,Rwb rwb){
		//检查传入的quartz表达式
		if((Conts.FD_GZLX_WJ != rwb.getFd_lx()) && ajaxCheckFd_ddpl(rwb.getFd_ddpl())==false) return "cronError";
		//转换传入的时间参数
		SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			rwb.setFd_scqdsj(ymdhms.parse(fd_scqdsj_n));
			if(Conts.FD_GZLX_WJ != rwb.getFd_lx()){
				rwb.setFd_sjsjc(ymdhms.parse(fd_sjsjc_n));
			}
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return "failure";
		}
		//设置更新信息
		Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
		rwb.setFd_gxr(yhb.getId());
		rwb.setFd_gxsj(new Date());
		
		//如果传送过来的任务表id不为空 则说明是编辑功能,做更新操作
		if(StringUtils.isNotBlank(rwb.getId())){
			rwbService.updateAndChangeStatus(rwb);
		}else{
			if(Conts.FD_GZLX_WJ != rwb.getFd_lx())rwb.setFd_sjldw(Conts.SJLDW_MINITUE);
			rwb.setId(null);
			rwbService.saveAndChangeStatus(rwb);
		}
		return "success";
	}
	
	@RequestMapping(value="toCollectScheduleManageJSP")
	public ModelAndView toCollectScheduleManageJSP(){
		ModelAndView mav = toTaskScheduleManageJSP("0");
		return mav;
	}
	
	@RequestMapping(value="toHandleScheduleManageJSP")
	public ModelAndView toHandleScheduleManageJSP(){
		ModelAndView mav = toTaskScheduleManageJSP("1");
		return mav;
	}
	
	@RequestMapping(value="toDigScheduleManageJSP")
	public ModelAndView toDigScheduleManageJSP(){
		ModelAndView mav = toTaskScheduleManageJSP("2");
		return mav;
	}
	
	
	public ModelAndView toTaskScheduleManageJSP(String fd_lx){
		ModelAndView mav = new ModelAndView();
		mav.addObject("fd_lx", fd_lx);
		mav.setViewName("/task/taskScheduleManage");
		return mav;
	}
	
	/**
	 * 跳转至新增或编辑页面
	 * @param fd_lx
	 * @param id
	 * @return
	 */
	@RequestMapping(value="ajaxCreateScheduleJSP")
	@ResponseBody
	public Map<String,Object> toCreateScheduleJSP(String fd_lx,String id){
		Map <String,Object>retMap = new HashMap<String,Object>();
		//编辑操作     如果存在任务id  则跳转到编辑界面,主动填写输入框
		if(StringUtils.isNotBlank(id)){
			Rwb rwb = rwbService.get(id);
			retMap.put("rwbObj", rwb);
			Gzpz gzpz = gzpzService.get(rwb.getFd_gz_id());
			retMap.put("fd_gzmc", gzpz.getFd_gzmc());
			retMap.put("editorFlag", "1");
			if("1".equals(fd_lx)){
				List<Map<String, Object>> collectList = rwbService.getSelectedCollectTaskList(id);
				retMap.put("collectList", collectList);
			}
			return retMap;
		}
		
		//添加操作
		if("1".equals(fd_lx)){
			List<Map<String, Object>> collectList = new ArrayList<Map<String, Object>>();
			retMap.put("collectList", collectList);
		}
		return retMap;
	}
	
	@RequestMapping(value="changeTaskStatus")
	@ResponseBody
	public String changeTaskStatus(String id ,String status){
		return 	rwbService.changeTaskStatus(id,status);
	}
	
	@RequestMapping(value="ajaxQzrwRecord")
	@ResponseBody
	public Map<String,Object> ajaxQzrwRecord(PageConfig pc,String ids,String qzrw_mc){
		List<Map<String,Object>>list  = rwbService.getCollectionTaskList(pc,ids,qzrw_mc);
		Map <String,Object>retMap = new HashMap<>();
		retMap.put("list", list);
		retMap.put("total",pc.getRowCount());
		return retMap ;
	}
	
	

}
