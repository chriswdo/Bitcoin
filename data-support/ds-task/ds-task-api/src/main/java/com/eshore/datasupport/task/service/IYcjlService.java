package com.eshore.datasupport.task.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.task.pojo.Ycjl;

/**
 * 
 */
public interface IYcjlService extends IBaseService<Ycjl> {
	List<Map<String,Object>>       exportYcdata(String idd);
}
