package com.eshore.datasupport.metadata.dao;

import com.eshore.datasupport.metadata.pojo.Zt;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
*generated by tools 
*
*/
public interface IZtDao extends IBaseDao<Zt> {

    /**
     * 获取所有主题的信息
     * @param pc 分页信息
     * @return
     */
	List<Map<String,Object>> getAllZtdata( PageConfig pc);

    /**
     *  获取含有特定"主题名称"的主题的信息
     * @param ztmc 主题名称
     * @param pc
     * @return
     */
    List<Map<String,Object>> getZtdataByztmc(String ztmc,PageConfig pc);

    /**
     * 删除主题
     * @param id 主键id
     * @return
     */
    int deleteZt(String id);

    /**
     * 新增主题
     * @param z 主题对象
     */
    void addNewZt(Zt z);

    /**
     * 由id获取单个主题信息
     * @param id
     * @return
     */
    Zt getZtdata(String id);

    /**
     * 更新单个主题信息
     * @param z
     */
    void updateZt(Zt z);
}
