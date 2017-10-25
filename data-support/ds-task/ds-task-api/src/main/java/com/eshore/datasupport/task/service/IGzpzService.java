package com.eshore.datasupport.task.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.RuleConfigVo;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.task.pojo.Gzpz;
import com.eshore.datasupport.task.vo.GzpzParams;
import com.eshore.datasupport.task.vo.RjobVo;

/**
 * 
 */
public interface IGzpzService extends IBaseService<Gzpz> {
	/**
	 * 通过规则类型查询规则配置表
	 * @param pc  分页
	 * @param lx  规则类型(0 采集 1 处理 2挖掘)
	 * @return
	 */
//	List<Map<String, Object>>     getRulelistdata(PageConfig pc,int lx);
	/**
	 * 查询数据源表
	 */
	//List<Map<String, Object>>  getDatainpage();
	/**
	 * 根据数据源ID查询元数据表
	 * @param ids  数据源ID
	 * @return
	 */
	List<Ysjb>     getSrbiao(String ids);
	/**
	 * 保存采集规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	String    getgzlistBy(GzpzParams params);
	/**
	 * 得到采集规则job list
	 * @param pc
	 * @param jobtxt 规则名称
	 * @return
	 */
	List<RjobVo>   getJoblist(PageConfig pc,String jobtxt,String sryid,String scyid);
	/**
	 * 得到处理规则job list
	 * @param pc
	 * @param jobtxt 规则名称
	 * @return
	 */
	List<RjobVo>   getclJoblist(PageConfig pc,String jobtxt,String sryid,String scyid);
	//List<Map<String, Object>>  getRuledataByid(String idu);
	/**
	 * 得到调度规则job list
	 * @param pc
	 * @param jobtxt 规则名称
	 * @return
	 */
	List<RjobVo>   getddJoblist(PageConfig pc,String jobtxt,String sryid,String scyid);
	/**
	 *查询规则配置表
	 * @param pc 分页
	 * @param gzm 规则名称
	 * @param lx 类型
	 * @return
	 */
	List<RuleConfigVo>   getRulelistdataBygzmc(PageConfig pc,String gzm,int lx);
	/**
	 * 保存调度规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	String saveddRuledata(GzpzParams params);
	/**
	 * 保存处理规则信息
	 * @param params  传入参数类
	 * @return 返回值
	 */
	String saveclRuledata(GzpzParams params) ;
	/**
	 * 更新规则配置表的采集规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	String updateRuledata(GzpzParams params,String gzid);
	/**
	 * 更新规则配置表的处理规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	String updateclRuledata(GzpzParams params,String gzid);
	/**
	 * 更新规则配置表的调度规则
	 * @param params  参数类
	 * @param gzid    规则ID
	 * @return
	 */
	String updateddRuledata(GzpzParams params,String gzid);
	/**
	 * 根据条件查询gzpz
	 * @param params
	 * @param pc
	 * @return
	 */
	public List<Map<String, Object>> findGzpzList(Map<String,Object>params,PageConfig pc);
	
	/**
	 * 根据条件查询rwb
	 * @param params
	 * @param pc
	 * @return
	 */
	public List<Map<String, Object>> findRwbList(Map<String,Object>params, PageConfig pc);
}
