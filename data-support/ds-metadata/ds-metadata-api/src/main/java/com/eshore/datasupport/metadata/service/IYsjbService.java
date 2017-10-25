package com.eshore.datasupport.metadata.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjbDescribeVo;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.pojo.Ysjzd;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 * 
 */
public interface IYsjbService extends IBaseService<Ysjb> {
	/**
	 * 根据数据源ID查元数据表
	 * @param sjy  数据源ID
	 * @param pc   分页
	 * @return
	 */
	List<Ysjb> getMetedataTable(String sjy,PageConfig pc);
	/**
	 * 得到数据源list
	 * @return
	 */
	//List<Map<String, Object>> getDatayuan();
	/**
	 * 得到远程表描述信息
	 * @param bm   表名
	 * @param sjy  数据源
	 * @return
	 */
	List<YsjbDescribeVo> getbiaoSql(String bm,String sjy);
	boolean findAnyBysjyId(String id);

	/**
	 * 获取相对应的表的描述信息
	 * @param fd_sjy_id 数据源主键
	 * @param bm 表名
	 * @return
	 */
	String getTableComment(String fd_sjy_id,String bm);

	/**
	 * 获取第一个匹配的ID
	 * @param fd_bm 表名
	 * @param fd_ms 描述
	 * @return
	 */
	String getTableId(String fd_bm,String fd_ms);
}
