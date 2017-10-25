package com.eshore.datasupport.common.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Zypz;
import com.eshore.khala.core.api.IBaseService;

/**
 * 
 */
public interface IZypzService extends IBaseService<Zypz> {

	List<Zypz> getParentMenu();

	Map<String, String> getIconInfo();

	List<Zypz> ajaxMenuListById(String id);

	List<Map<String, Object>> getSortedList(List<Zypz> list);

	void deletexZypzRecord(String id);
	
	Map<String, String> deleteArrayZypz(String idArrStr);

}
