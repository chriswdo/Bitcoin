package com.eshore.datasupport.metadata.service;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Xxfl;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.vo.MessageVo;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

/**
 *
 */
public interface IXxflService extends IBaseService<Xxfl> {
/**
 * 得到信息分类表信息
 * @param zt  主题ID
 * @param meg  信息
 * @param pc  分页
 * @return 
 */
    List<MessageVo> getMessagedata(String zt, String meg, PageConfig pc);

    List<Map<String, Object>> getZhutidata();

   // List<Map<String, Object>> getmessageDataByid(String idd);
/** 
 *  通过表名查询
 * @param bm  表名
 * @return
 */
    List<Xxfl> getDataBybm(String bm);
/**
 * 根据信息分类表的ID得到信息分类字段信息
 * @param megid  信息ID(当信息ID为空，默认为"xzxx")
 * @return
 */
    List<YsjzdVo> getxxmetadata(String megid);
    /**
     * 通过信息ID得到信息字段表的对应信息表的字段
     * @param xxid  信息表ID
     * @return
     */
  List<Map<String, Object>>  getmetaDatainpage(String megid);
    /**
     * 通过信息ID得到信息字段表的对应信息表的字段
     * @param xxid  信息表ID
     * @return
     */
    List<YsjzdVo> getxxmetadataBymc(String megid, String mc);
    /**
     * 更新信息分类远程库表和本地库表的信息
     * @param params  参数请求类
     * @param userid  用户ID 
     * @return
     */
    String updateMesagedata(RequestParams params, String userid);
    /**
     * 添加信息分类远程库表和本地库表的信息
     * @param params  参数请求类
     * @param userid  用户ID 
     * @return
     */
    String addMesagedata(RequestParams params, String userid);
/**
 * 更新信息分类表
 * @param xxid  信息ID
 * @param xxzt  信息主题
 * @param xxmc  信息名称
 * @param xxms  信息描述
 * @param xxbm  信息表名
 * @param userid 用户ID
 * @return
 */
    String updatexxflOFdata(String xxid, String xxzt, String xxmc, String xxms, String xxbm, String userid);
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
 * 远程创建表格的方法
 * @param requestParams  请求的参数类
 * @param jsonadd        前台传入的参数
 * @param userid         用户ID
 * @return
 */
    String addxxflMeg(RequestParams requestParams, List<Xxzd> jsonadd, String userid);

    List<Map<String, Object>> getIDfromxxfl(String xxbm);

    boolean findAnyBysjyId(String id);
}
