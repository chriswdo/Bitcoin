package com.eshore.datasupport.common.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Gj;
import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.vo.JkdyVo;

/**
 * 
 */
public interface IJkdyService extends IBaseService<Jkdy> {

	List<JkdyVo> getInterfaceMeg(String jkname, PageConfig pc);
	String changeInterdaceStatus(String id, String status);
	String  savexJkMessage(Jkdy jk,String userid);
	String  updateJkMessage(Jkdy jk,String userid);
	List<Jkdy>  getDataBynamecode(String code);


}
