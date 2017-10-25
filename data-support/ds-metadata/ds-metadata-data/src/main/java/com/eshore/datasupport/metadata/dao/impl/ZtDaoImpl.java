package com.eshore.datasupport.metadata.dao.impl;

import com.eshore.datasupport.metadata.dao.IZtDao;
import com.eshore.datasupport.metadata.pojo.Zt;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/4/21.
 */
@Repository
public class ZtDaoImpl extends JpaDaoImpl<Zt> implements IZtDao{
    /**
     * 获取所有主题的信息
     * @param pc 分页信息
     * @return
     */
    @Override
    public List<Map<String,Object>> getAllZtdata( PageConfig pc) {
        String sql="SELECT * FROM DSJZC_ZT";
        List<Map<String, Object>> relist= iSQLQuery.query(sql,pc);
        return relist;
    }

    /**
     *  获取含有特定"主题名称"的主题的信息
     * @param ztmc 主题名称
     * @param pc
     * @return
     */
    @Override
    public List<Map<String,Object>> getZtdataByztmc(String ztmc, PageConfig pc) {
        String sql = "select * from DSJZC_ZT where 1=1 and fd_ztmc like '%"+ztmc+"%'";
        List<Map<String,Object>> relist = iSQLQuery.query(sql,pc);
        return relist;
    }

    /**
     * 删除主题
     * @param id 主键id
     * @return
     */
    @Override
    public int deleteZt(String id) {
        String sql = "delete from DSJZC_ZT where DSJZC_ZT.id = "+id;
        int i = iSQLQuery.getCount(sql);
        return i;
    }

    /**
     * 新增主题
     * @param z 主题对象
     */
    @Override
    public void addNewZt(Zt z) {
        this.save(z);
    }

    /**
     * 由id获取单个主题信息
     * @param id
     * @return
     */
    @Override
    public Zt getZtdata(String id) {
        return get(id);
    }

    /**
     * 更新单个主题信息
     * @param z
     */
    @Override
    public void updateZt(Zt z) {
        this.update(z);
    }
}
