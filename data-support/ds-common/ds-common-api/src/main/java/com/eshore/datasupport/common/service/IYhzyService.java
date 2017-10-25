package com.eshore.datasupport.common.service;

import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Yhzy;
import com.eshore.datasupport.common.pojo.Zypz;

/**
 * 
 */
public interface IYhzyService extends IBaseService<Yhzy> {

	List listxMenuById(String id, String basePath);

	List ajaxZtreeMenuListById(String id, String string);

}
