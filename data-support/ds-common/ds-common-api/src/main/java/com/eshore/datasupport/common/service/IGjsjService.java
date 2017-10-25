package com.eshore.datasupport.common.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Gjsj;

/**
 * 
 */
public interface IGjsjService extends IBaseService<Gjsj> {

	List<Map<String, String>> ajaxHomeGjInfo(PageConfig pc);

}
