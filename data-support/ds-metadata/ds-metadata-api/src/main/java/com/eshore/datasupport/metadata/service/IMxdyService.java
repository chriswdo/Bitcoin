package com.eshore.datasupport.metadata.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Jcmxdy;
import com.eshore.datasupport.metadata.pojo.Xxfl;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.pojo.Ysjzd;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 *
 */
public interface IMxdyService extends IBaseService<Jcmxdy> {
    /**
     * 得到模型定义的信息
     * @param zt 数据库ID
     * @param meg 信息ID
     * @param pc 分页类
     * @return
     * 
     */
	List<Jcmxdy> getmxdyMessagedata(String zt, String meg, PageConfig pc);
   /**
    * 得到主题list
    * @return
    */
    List<Map<String, Object>> getZhutidata();
  /**
   * 查询模型定义表通过ID
   * @param idd 模型定义表ID
   * @return
   */
  //  List<Map<String, Object>> getjcmxmessageDataByid(String idd);
    /**
     * 得到编辑界面展示的数据库字段信息
     * @param megid 模型定义表ID
     * @return
     */
    List<Map<String, Object>> getjcmxmetadata(String megid);
    /**
     * 查询具体字段信息
     * @param megid  信息ID
     * @param mc   字段名称
     * @return
     */
    List<YsjzdVo> getmxdymetadataBymc(String megid, String mc);

    List<Jcmxdy> getDataBybm(String bm);
    /**
     * 更新模型定义远程表信息
     * @param params  请求参数类
     * @param userid  用户ID
     * @return
     */
    String updatemxdyMegdata(RequestParams params, String userid);
  /**
   * 添加模型定义远程表信息
   * @param params  请求参数类
   * @param userid  用户ID
   * @return
   */
    String addjcmxMegdata(RequestParams params, String userid);
    /**
     *  更新本地的模型定义数据表
     * @param xxid  模型定义ID
     * @param xxzt  库信息
     * @param xxmc  模型定义名称
     * @param xxms  表描述
     * @param xxbm  本地模型定义表名
     * @param userid 用户ID
     * @return
     */
    String updatexxflOFdata(String xxid, String xxzt, String xxmc, String xxms, String xxbm, String userid);

    /**
     * 添加模型定义远程表的执行sql信息
     * @param params  请求参数类
     * @param jsonadd  添加的字段信息
     * @param userid  用户ID
     * @return
     */
    String addxxflMeg(RequestParams params, List<Xxzd> jsonadd, String userid);

    List<Map<String, Object>> getIDfromxxfl(String xxbm);
	/**
	 * 执行远程修改表信息的方法
	 * @param xxzt  模型定义的数据库ID
	 * @param xxid  模型分类表ID
	 * @param xxbm  模型分类表名
	 * @param xxms  表描述
	 * @param addcolumns 页面新增的字段list
	 * @param editcolumns 页面编辑的字段list
	 * @return
	 */
	String createTableOrcolumns(String xxzt,String xxid, String xxbm, String xxms, List<Xxzd> addcolumns, List<Xxzd> editcolumns);

}
