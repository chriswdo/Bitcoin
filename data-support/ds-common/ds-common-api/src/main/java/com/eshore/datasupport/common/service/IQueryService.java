package com.eshore.datasupport.common.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.vo.JkVo;
import com.eshore.khala.core.api.IBaseService;

public interface IQueryService extends IBaseService<Jkdy>{

	/**
	 * 检查该jkcode是否存在，
	 * 如果存在返回该对象，
	 * 如果不存在，则返回null
	 * @param jkCode
	 * @return
	 */
	JkVo getJkdyByJkcode(String jkCode);

	/**
	 * 执行接口并
	 * @param jkvo
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	List<Map<String, Object>> executeSelectSql(JkVo jkvo) throws Exception;
	
	/**
	 * 检查sql中是否含有SELECT
	 * @param jkvo
	 * @return
	 */
	boolean checkSqlContainSELECT(JkVo jkvo);

	/**
	 * 执行sql
	 * @param jkvo
	 */
	void executeSql(JkVo jkvo);

}
