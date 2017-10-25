package com.eshore.datasupport.metadata.service;

import com.eshore.datasupport.metadata.vo.DataSourceTs;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Ysjzd;

/**
 * 
 */
public interface IYsjzdService extends IBaseService<Ysjzd> {
	/**
	 * 查询元数据字段字段信息
	 * @param parm1  字段ID
	 * @param parm2 表ID
	 * @return
	 */
	List<Map<String, Object>>  editysjzdData(String parm1,String parm2);
	int saveysjzdData(String pa1,String pa2);
	/**
	 * 通过表名得到元数据字段数据
	 * @param bm  表名
	 * @return
	 */
	List<Ysjzd> getysjzdData(String bm);
	/**
	 *  得到远程数据表字段信息
	 * @param sjy  数据源
	 * @param bm   表名
	 * @return
	 */
	List<YsjzdVo> getDatatosyn(String sjy,String bm);
	/**
	 * 同步远程表字段信息到本地库表
	 * @param opt  数据源ID
	 * @param synbm  表名
	 * @param userid  用户ID
	 * @param bms    表描述
	 * @return
	 */
	String synymetaData(String sjy,String synbm,String userid,String bms);
	List<YsjzdVo> getysjzdDataBybm(String id,String bm,String sjy);

	List<DataSourceTs> getDataSourceTs(String id);
}
