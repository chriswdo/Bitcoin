package com.eshore.datasupport.common.dao;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.vo.JkVo;
import com.eshore.khala.core.data.api.dao.IBaseDao;

public interface IQueryDao extends IBaseDao<Jkdy>{

	JkVo getJkdyByJkcode(String jkCode);

	List<Map<String, Object>> executeSelectSql(JkVo jkvo) throws Exception;

	void executeSql(JkVo jkvo);

}
