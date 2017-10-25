package com.eshore.datasupport.metadata.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Wddy;
import com.eshore.datasupport.metadata.service.IWddyService;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/wddyController")
public class WddyController extends BaseController<Wddy> {
	private  final Logger logger = Logger.getLogger(WddyController.class);

	@Autowired IWddyService wddyService;

	@Override	
	protected String getBasePath() {
		return "/datasupport";
	}

	@Override
	protected IBaseService<Wddy> getService() {
		return wddyService;
	}

	public String getModuleName() {
		return "元数据管理>元数据表";
	}
  @RequestMapping(value="getwdMessagedata")
  @ResponseBody
  /**
   * 得到维度定义的信息
   * @param meg 信息ID
   * @param pc 分页类
   * @return
   * @throws UnsupportedEncodingException
   */
  public Map<String,Object> getMessagedata(String meg,PageConfig  pc) throws UnsupportedEncodingException{
	  String megs=URLDecoder.decode(meg,"utf-8");
	    List<Wddy> relist=wddyService.getwdMessagedata(megs,pc);
		Map<String,Object> retMap = new HashMap<String ,Object>();
		retMap.put("list", relist);
		retMap.put("total",pc.getRowCount());	  
	   return retMap ;
	  
	  
  }
  
  @RequestMapping(value="getwddymetadata")
  @ResponseBody
  /**
   * 根据维度ID得到维度表信息
   * @param xxid  维度表ID
   * @return
   */
  public String getwddymetadata(String xxid) {
	  List<Map<String, Object>> relist=wddyService.getwddymetadata(xxid);	
		Map<String,Object> retMap = new HashMap<String ,Object>();
		retMap.put("rows", relist);	
		JSONObject jsonObject = JSONObject.fromObject(retMap);  
	   return jsonObject.toString() ;
  
  }
  
  @RequestMapping(value="checkCols")
  @ResponseBody
  /**
   * 判断字段名是否重复
   * @param xxid   维度ID
   * @param zdmc   字段名称
   * @return
   */
  public String checkCols(String xxid,String zdmc) {
	  String res="";
	  List<Map<String, Object>> relist=wddyService.getwddymetadata(xxid);
         for (Map<String, Object> map : relist) {
				 if(map.get("FD_ZDMC").toString().equals(zdmc.trim())){
					  res="repeat";
					  break;
				 }
			}
	   return res;
	 	  
  }
  
  @RequestMapping(value="getxxmetadataBymc")
  @ResponseBody
  /**
   * 通过ID，字段名称得到维度表的具体字段信息
   * @param xxid  维度ID
   * @param zdmc  字段名称
   * @return
   */
  public String getxxmetadataBymc(String xxid,String zdmc) {
	  List<YsjzdVo> relist=wddyService.getwdxxmetadataBymc(xxid,zdmc);	

	 if("VARCHAR".equals(relist.get(0).getZdmc())||"NUMBER".equals(relist.get(0).getZdmc())){
		 return "cddemo";
	 }else if("NUMERIC".equals(relist.get(0).getZdmc())){
		 return "jddemo";
	 }else{
		 return "datedemo";
	 }
	 	  
  }
  
	@RequestMapping(value = "getwddyDatainpage")
	@ResponseBody
	/**
	 * 跳转到维度定义新增页面
	 * @return
	 */
	public ModelAndView getwddyDatainpage() {
		  ModelAndView mv = new ModelAndView ();
		  mv.addObject("megid","xzxx");
		  mv.setViewName(getBasePath() + "/wddyyxz");
		  return mv ;
	}
  
//	@RequestMapping(value = "editwddymetadata")
//	@ResponseBody
//	/**
//	 * 跳转到维度定义编辑界面
//	 * @param megid  维度定义ID
//	 * @return
//	 */
//	public  Map<String, Object> editwddymetadata(String megid) {
//		  Map<String, Object> retMap = new HashMap<>();
//		  List<Map<String, Object>>  relist=wddyService.getmessageDataByid(megid);
//		  JSONArray arr=JSONArray.fromObject(relist);
//		  JSONObject  ja=arr.getJSONObject(0);
//	        retMap.put("relist", ja);
//	        return retMap;
//	}
	
  @RequestMapping(value="getwdDatapage")
  /**
   * 跳转到维度定义界面
   * @return
   */
  public ModelAndView getwdDatapage(){
	  ModelAndView mv = new ModelAndView ();
	  mv.setViewName(getBasePath() + "/wddy");
	  logger.info(getBasePath() + "/wddy");
	  return mv ;	  
	  
  } 
  
 
 
	
	@RequestMapping(value = "updatewddyMegdata")
	@ResponseBody
	/**
	 * 更新维度定义表远程库表和本地库表的信息
	 * @param params 参数请求类
	 * @param request 
	 * @return
	 */
	public String updatewddyMegdata(RequestParams params,HttpServletRequest request ) {	
		String res="";
		Yhb user=SessionUtil.getCurrentUserInfo(request);
		String userid=user.getId();
		res=wddyService.updatewddyMegdata(params,userid);		
		return res;
	}
	
	
	@RequestMapping(value = "addwddyMegdata")  
	@ResponseBody
	/**
	 * 添加维度定义表远程库表和本地库表的信息
	 * @param params 参数请求类
	 * @param request 
	 * @return
	 */
	public String addwddyMegdata(RequestParams params,HttpServletRequest request) {	
		String res="";
		Yhb user=SessionUtil.getCurrentUserInfo(request);
		String userid=user.getId();
		res=wddyService.addwddyMegdata(params,userid);	
		return res;
	}
	
	              
}
