package com.eshore.datasupport.metadata.dao;

import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/9.
 */
public interface IZdflDao extends IBaseDao<Zdfl> {
    /**
     * 获取所有字典分类的信息
     * @param pc 分页
     * @return
     */
    List<Map<String,Object>> getAllZdfldata( PageConfig pc);

    /**
     * 获取含有特定"字典分类名称"的主题的信息
     * @param zdflmc
     * @param pc
     * @return
     */
    List<Map<String,Object>> getZdfldataByzdflmc(String zdflmc,PageConfig pc);

    /**
     * 是否存在分类编码
     * @param flbm
     * @return
     */
    List<Map<String,Object>> isExistflbm(String flbm);

}
