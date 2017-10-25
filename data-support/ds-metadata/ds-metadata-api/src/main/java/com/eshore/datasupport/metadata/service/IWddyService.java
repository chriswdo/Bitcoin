package com.eshore.datasupport.metadata.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Wddy;
import com.eshore.datasupport.metadata.pojo.Xxzd;

import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 * 
 */
public interface IWddyService extends IBaseService<Wddy> {
	
	List<Wddy> getwdMessagedata(String meg,PageConfig pc);
	List<Map<String, Object>> getZhutidata();
	//List<Map<String, Object>> getmessageDataByid(String idd);
	  /**
	   * 根据维度ID得到维度表信息
	   * @param xxid  维度表ID
	   * @return
	   */
	List<Map<String, Object>>  getwddymetadata(String  megid);
	List<YsjzdVo> getwddymetadataForcompare(String megid);
	/**
	 * 通过表名查询维度定义表
	 * @param bm 表名
	 * @return
	 */
	List<Wddy>  getDataBybm(String  bm);
	  /**
	   * 通过ID，字段名称得到维度表的具体字段信息
	   * @param xxid  维度ID
	   * @param zdmc  字段名称
	   * @return
	   */
	List<YsjzdVo> getwdxxmetadataBymc(String megid,String mc);
	/**
	 * 更新维度定义表远程库表和本地库表的信息
	 * @param params 参数请求类
	 * @param userid  用户ID
	 * @return
	 */
	String updatewddyMegdata(RequestParams params,String userid);
	/**
	 * 执行远程修改表信息的方法
	 * @param xxid  信息ID
	 * @param xxbm  信息表名
	 * @param xxms  信息描述
	 * @param addcolumns 页面新增的字段list
	 * @param editcolumns 页面编辑的字段list
	 * @return
	 */
	    String createTableOrcolumns(String xxid,String xxbm, String xxms,List<Xxzd> addcolumns, List<Xxzd> editcolumns);
		/**
		 * 添加维度定义表远程库表和本地库表的信息
		 * @param params 参数请求类
		 * @param userid 用户ID 
		 * @return
		 */  
	String addwddyMegdata(RequestParams params,String userid);
	/**
	 * 更新本地信息分类表 
	 * @param xxid 信息ID
	 * @param xxmc 信息名称
	 * @param xxms 信息描述
	 * @param xxbm 信息表名
	 * @param userid 用户ID
	 * @return
	 */
	String updatexxflOFdata(String xxid, String xxmc, String xxms,String xxbm,String userid);
	String editxxmetadata(String xxbm,Xxzd xzd,String oper);
	/**
	 * 远程创建表格的方法
	 * @param requestParams  请求的参数类
	 * @param jsonadd        前台传入的参数
	 * @param userid         用户ID
	 * @return
	 */
	 String addxxflMeg(String xxuuid,String xxmc,String xxms,String xxbm,List<Xxzd> jsonadd,String userid);
	 List<Map<String, Object>>  getIDfromxxfl(String xxbm); 
}
