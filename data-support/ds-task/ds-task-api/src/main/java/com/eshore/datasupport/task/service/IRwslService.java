package com.eshore.datasupport.task.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.datasupport.task.vo.ExceptionRecordVo;

/**
 * 
 */
public interface IRwslService extends IBaseService<Rwsl> {
	public Rwsl findLastRwsl(String dsJobId,Date sjc);
	public void saveRWSL(Rwsl rwsl);
	/**
	 * 得到日志监控数据
	 * @param gzm  规则名称
	 * @param pc   分页
	 * @param lx  规则类型
	 * @return
	 */
	List<Map<String, Object>> getRulemonitordata(String gzm,PageConfig pc,int lx);
	/**
	 * 得到任务实例表的数据
	 * @param idd
	 * @param pc
	 * @return
	 */
	List<Map<String, Object>> getRuledetail(String idd,PageConfig pc);
	/**
	 * 得到异常记录表的数据
	 * @param idd
	 * @param pc
	 * @return
	 */
	List<ExceptionRecordVo> getYcjudetail(String idd,PageConfig pc);
	//List<Map<String, Object>>    getYcjuofycsj(String idd);
	
	//获取首页数据库采集数量，异常数量统计信息学
	public List<Map<String, Object>> ajaxRwslStatistics(PageConfig pc);
	
	/**
	 * 首页图表展示rwsl统计信息
	 * @param retMap  带入返回的map
	 */
	public void  ajaxHomeRwslStatisticForEchart(Map<String, Object> retMap);
}
