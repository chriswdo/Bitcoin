package com.eshore.datasupport.metadata.service;

import com.eshore.datasupport.metadata.pojo.Zt;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/4/21.
 */
public interface IZtService extends IBaseService<Zt>{
    /**
     * 获取所有主题的信息
     * @param pc 分页
     * @return
     */
    List<Map<String,Object>> getAllZtdata(PageConfig pc);

    /**
     * 获取含有特定"主题名称"的主题的信息
     * @param ztmc 主题名称
     * @param pc
     * @return
     */
    List<Map<String,Object>> getZtdataByztmc(String ztmc,PageConfig pc);

    /**
     * 新增主题
     * @param fd_ztmc 主题名称
     * @param fd_zt 状态
     * @param fd_ms 描述
     * @param fd_cjr 创建人
     */
    void addNewZt(String fd_ztmc,String fd_zt,String fd_ms,String fd_cjr);

    /**
     * 由id获取单个主题信息
     * @param id
     * @return
     */
    Zt getZtdata(String id);

    /**
     * 更新单个主题信息
     * @param id 主键Id
     * @param fd_ztmc 主题名称
     * @param fd_zt 状态
     * @param fd_ms 描述
     * @param fd_gxr 更新人
     */
    void updateZt(String id,String fd_ztmc,String fd_zt,String fd_ms,String fd_gxr);

    /**
     * 删除主题
     * @param id
     */
    void deleteZt(String id);
}
