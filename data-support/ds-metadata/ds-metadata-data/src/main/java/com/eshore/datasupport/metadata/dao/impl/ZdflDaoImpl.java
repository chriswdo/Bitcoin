package com.eshore.datasupport.metadata.dao.impl;

import com.eshore.datasupport.metadata.dao.IZdflDao;
import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/9.
 */
@Repository
public class ZdflDaoImpl extends JpaDaoImpl<Zdfl> implements IZdflDao {
    /**
     * 获取所有字典分类的信息
     * @param pc 分页
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdfldata(PageConfig pc) {
        String sql="SELECT * FROM DSJZC_ZDFL ORDER BY FD_FLBM";
        List<Map<String, Object>> relist= iSQLQuery.query(sql,pc);
        return relist;
    }

    /**
     * 获取含有特定"字典分类名称"的主题的信息
     * @param zdflmc
     * @param pc
     * @return
     */
    @Override
    public List<Map<String, Object>> getZdfldataByzdflmc(String zdflmc, PageConfig pc) {
        String sql = "select * from DSJZC_ZDFL where 1=1 and fd_flmc like '%"+zdflmc+"%' ORDER BY FD_FLBM";
        List<Map<String,Object>> relist = iSQLQuery.query(sql,pc);
        return relist;
    }

    /**
     * 是否存在分类编码
     * @param flbm
     * @return
     */
    @Override
    public List<Map<String, Object>> isExistflbm(String flbm) {
        List<Map<String, Object>> list=iSQLQuery.query("SELECT * from  DSJZC_ZDFL where fd_flbm = ? ",new Object[]{flbm});

        return list;
    }


}
