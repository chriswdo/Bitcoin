package com.eshore.datasupport.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.service.IQueryService;
import com.eshore.datasupport.common.vo.JkVo;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/query")
public class QueryController extends BaseController<Jkdy> {
	private static final Logger logger = Logger.getLogger(QueryController.class);
	
	@Autowired IQueryService jkdyService;
	
	@Override
	protected String getBasePath() {
		return null;
	}

	@Override
	protected IBaseService<Jkdy> getService() {
		return null;
	}

	@RequestMapping(value = "/{jkName}")
	@ResponseBody
	public Map<String,Object> getDataBySpecifiedSql(@PathVariable("jkName") String jkCode){
		
		//定义返回数据结构
		Map<String,Object> map = new HashMap<String,Object>();
		String resultCode = "ResultCode";
		String resultMsg  = "ResultMsg";
		String dataCount  = "DataCount";
		String dataList   = "DataList";
		String startNum  = "startNum";
		String endNum    = "endNum";
		
		try{
			//检查该接口是否存在、是否可用
			JkVo jkvo = jkdyService.getJkdyByJkcode(jkCode);
			if(jkvo == null){
				map.put(resultCode, "10");
				map.put(resultMsg, "接口不存在!");
				return map;
			}
			
			//检查该接口是否可用
			if(!"Y".equals(jkvo.getJk_zt())){
				map.put(resultCode, "20");
				map.put(resultMsg, "接口不可用!");
				return map;
			}
			
			HttpServletRequest request = this.getRequest();
			//检查是否带有分页参数
			if(jkdyService.checkSqlContainSELECT(jkvo)){
				String startNumValue   =  request.getParameter(startNum);
				String endNumValue     =  request.getParameter(endNum);
				if(!(StringUtils.isNotBlank(startNumValue)&&StringUtils.isNumeric(startNumValue) &&
						StringUtils.isNotBlank(endNumValue)&& StringUtils.isNumeric(endNumValue))){
					map.put(resultCode, "30");
					map.put(resultMsg, "分页参数错误!");
					return map;
				}
				jkvo.setPage_suf(jkvo.getPage_suf().replace("#{"+startNum+"}", startNumValue));
				jkvo.setPage_suf(jkvo.getPage_suf().replace("#{"+endNum+"}", endNumValue));
			}
			
			//sql参数检查
			String keywords = jkvo.getKeywords();
			if(StringUtils.isNotBlank(keywords)){
				String [] keywordsArr = keywords.split("#");
				for(String str : keywordsArr){
					String param = request.getParameter(str);
					if(StringUtils.isBlank(param)){
						map.put(resultCode, "40");
						map.put(resultMsg, "sql参数错误:"+str+"未定义");
						return map;
					}
					jkvo.setSql_info(jkvo.getSql_info().replace("#{"+str+"}", param));
				}
			}
			
			/*执行sql语句*/
			if(jkdyService.checkSqlContainSELECT(jkvo)){
				//拼接执行的sql,带上分页逻辑     含有SELECT
				List<Map<String, Object>> retList=null;
				try {
					retList = jkdyService.executeSelectSql(jkvo);
				} catch (Exception e) {
					e.printStackTrace();
					map.put(resultCode, "50");
					map.put(resultMsg, e.getMessage());
					return map;
				}
				map.put(resultCode, "00");
				map.put(resultMsg, "成功!");
				map.put(dataCount, retList.size());
				map.put(dataList, retList);
				return map;
			}else{
				//执行sql
				try {
					jkdyService.executeSql(jkvo);
				} catch (Exception e) {
					e.printStackTrace();
					map.put(resultCode, "50");
					map.put(resultMsg, e.getMessage());
					return map;
				}
				map.put(resultCode, "00");
				map.put(resultMsg, "成功!");
				map.put(dataCount, 0);
				map.put(dataList, null);
				return map;
			}

		}catch(Exception e){
			logger.error(e.getMessage());
			map.put(resultCode, "99");
			map.put(resultMsg, "未知错误!");
			return map;
		}
	}


}
