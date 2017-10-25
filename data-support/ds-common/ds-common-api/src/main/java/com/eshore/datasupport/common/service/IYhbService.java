package com.eshore.datasupport.common.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 * 
 */
public interface IYhbService extends IBaseService<Yhb> {

	List<Yhb> listByYhmc(Map<String,String> params,PageConfig pc);

	/**
	 * md加密密码
	 * @param fd_mm 密码
	 * @return  返回加密后的字符串
	 */
	String getMd5Password(String fd_mm);

	Yhb getYhbByFd_dlmc(String fd_dlmc);

	boolean Fd_dlmcIsExist(String fd_dlmc);
	
	/**
	 * 得到菜单的嵌套结构
	 * @param list
	 * @param nodes
	 */
	void getNodes(List<Map<String,Object>> list,List<Map<String,Object>> nodes);

	void modifyxSjybRecord(Yhb yhbDb,String nodes);

	void savexYhbRecord(Yhb yhb,String nodes);

}
