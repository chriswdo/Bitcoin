package com.eshore.datasupport.common.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Gj;
import com.eshore.datasupport.common.pojo.Yhb;

/**
 * 
 */
public interface IGjService extends IBaseService<Gj> {

	List<Gj> listxAlarmRecord(Map<String, String> params, PageConfig pc);

	List<Map<String, Object>> ajaxTaskInfo(Map<String, Object> params, PageConfig pc);

}
