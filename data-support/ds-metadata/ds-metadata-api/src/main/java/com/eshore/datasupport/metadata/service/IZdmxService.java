package com.eshore.datasupport.metadata.service;

import com.eshore.datasupport.metadata.pojo.Zdmx;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/10.
 */
public interface IZdmxService extends IBaseService<Zdmx>{
    /**
     * 获取所有字典明细信息
     * @param pc 分页
     * @return
     */
    List<Map<String,Object>> getAllZdmxdata( PageConfig pc);

    /**
     * 通过分类编码，字典名称获取符合条件的字典明细
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param pc 分页
     * @return
     */
    List<Map<String,Object>> getZdmxdataByzdmc(String fd_flbm, String fd_zdmc, PageConfig pc);

    /**
     * 删除字典明细
     * @param id
     */
    void deleteZdmx(String id);

    /**
     * 批量删除字典明细
     * @param list
     */
    void deleteMulZdmx(String list);

    /**
     * 更新字典明细
     * @param id 主键id
     * @param fd_zdmc 字典名称
     * @param fd_gxr 更新人
     * @param fd_zdms 字典描述
     */
    void updateZdmx(String id,String fd_zdmc,String fd_gxr,String fd_zdms);

    /**
     * 保存字典明细
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param fd_zdbm 字典编码
     * @param fd_cjr 创建人
     * @param fd_zdms 字典描述
     */
    void saveZdmx(String fd_flbm,String fd_zdmc,String fd_zdbm,String fd_cjr,String fd_zdms);

    /**
     * 判断是否存在字典编码
     * @param fd_zdbm
     * @param fd_flbm
     * @return
     */
    boolean isExistzdbm(String fd_zdbm, String fd_flbm);

    /**
     * 获取所有字典分类的信息
     * @return
     */
    List<Map<String,Object>> getAllZdflmc();

    /** 判断分类编码是否存在
     * @param fd_flbm 分类编码
     * @return
     */
    boolean isExistFlbm(String fd_flbm);
}
