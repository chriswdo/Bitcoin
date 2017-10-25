package com.eshore.datasupport.metadata.service;

import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/9.
 */

public interface IZdflService extends IBaseService<Zdfl> {
    /**
     * 获取所有字典分类的信息
     * @param pc 分页
     * @return
     */
    List<Map<String,Object>> getAllZdfldata( PageConfig pc);

    /**
     * 获取含有特定"字典分类名称"的主题的信息
     * @param fd_flmc
     * @param pc
     * @return
     */
    List<Map<String,Object>> getZdfldataByzdflmc(String fd_flmc,PageConfig pc);

    /**
     * 删除单个字典分类信息
     * @param id
     */
    void deleteZdfl(String id);

    /**
     * 更新字典分类
     * @param id 主键
     * @param fd_flmc 分类名称
     * @param fd_zt 状态
     * @param fd_gxr 更新人
     */
    void updateZdfl(String id,String fd_flmc,String fd_zt,String fd_gxr);

    /**
     * 保存字典分类
     * @param fd_flmc 分类名称
     * @param fd_flbm 分类编码
     * @param fd_zt 状态
     * @param fd_cjr 创建人
     */
    void saveZdfl(String fd_flmc,String fd_flbm,String fd_zt,String fd_cjr);

    /**
     * 是否存在分类编码
     * @param fd_flbm
     * @return
     */
    boolean isExistflbm(String fd_flbm);

    /**
     * 获取字典分类信息通过主键id
     * @param id
     * @return
     */
    Zdfl getZdfldataByID(String id);
}
