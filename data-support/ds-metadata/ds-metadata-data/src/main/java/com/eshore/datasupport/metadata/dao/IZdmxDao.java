package com.eshore.datasupport.metadata.dao;

import com.eshore.datasupport.metadata.pojo.Zdmx;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/10.
 */
public interface IZdmxDao extends IBaseDao<Zdmx> {
    /**
     * 获取字典明细所有信息，按照分类编码，字段编码排序
     * @param pc  分页
     * @return
     */
    List<Map<String,Object>> getAllZdmxdata( PageConfig pc);

    /**
     * 根据分类编码，字典名称查找出所有符合条件的字典明细信息
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param pc
     * @return
     */
    List<Map<String,Object>> getZdmxdataByzdmc(String fd_flbm,String fd_zdmc,PageConfig pc);

    /**
     * 判断是否存在字典编码
     * @param fd_zdbm 字典编码
     * @param fd_flbm 分类编码
     * @return
     */
    List<Map<String,Object>> isExistzdbm(String fd_zdbm,String fd_flbm);

    /**
     * 获取所有字典分类的信息
     * @return
     */
    List<Map<String,Object>> getAllZdflmc();

    /** 判断分类编码是否存在
     * @param fd_flbm 分类编码
     * @return
     */
    List<Map<String,Object>> isExistFlbm(String fd_flbm);
}
