package com.eshore.datasupport.metadata.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 * 
 */
public interface ISjybService extends IBaseService<Sjyb> {
	
	/**
	 * 测试连接
	 * @param sjybRecord 
	 * @return
	 */
	public String checkConnecting(Sjyb sjybRecord);
	
	/**
	 * 保存数据源
	 * @param sjyb
	 * @return
	 */
	public String saveSjybRecord(Sjyb sjyb);

	/**
	 * 删除数据源以及在kettle中的信息
	 * @param id
	 */
	public void deleteSjybAndR_databaseInfo(String id);

	/**
	 * 收缩sjyb信息
	 * @param hashMap
	 * @param pc
	 * @return
	 */
	public List<Sjyb> listSjybRecord(Map<String,String> hashMap, PageConfig pc);

	/**
	 * 更新sjyb信息
	 * @param sjybRecord
	 */
	public void updateSjybRecord( Sjyb sjybRecord);

}
