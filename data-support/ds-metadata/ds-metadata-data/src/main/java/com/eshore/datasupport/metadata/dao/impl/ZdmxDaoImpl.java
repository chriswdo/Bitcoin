package com.eshore.datasupport.metadata.dao.impl;

import com.eshore.datasupport.metadata.dao.IZdmxDao;
import com.eshore.datasupport.metadata.pojo.Zdmx;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/10.
 */
@Repository
public class ZdmxDaoImpl extends JpaDaoImpl<Zdmx> implements IZdmxDao{
    /**
     * 获取字典明细所有信息，按照分类编码，字段编码排序
     * @param pc  分页
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdmxdata(PageConfig pc) {
        String sql="SELECT x.id,x.fd_zdmc,x.fd_zdbm,x.fd_zdms,x.fd_flbm,l.fd_flmc FROM DSJZC_ZDMX x join DSJZC_ZDFL l on x.fd_flbm = l.fd_flbm ORDER BY x.FD_FLBM,x.FD_ZDBM";
        List<Map<String, Object>> relist= iSQLQuery.query(sql,pc);
        return relist;
    }

    /**
     * 根据分类编码，字典名称查找出所有符合条件的字典明细信息
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param pc
     * @return
     */
    @Override
    public List<Map<String, Object>> getZdmxdataByzdmc(String fd_flbm, String fd_zdmc, PageConfig pc) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x.id,x.fd_zdmc,x.fd_zdbm,x.fd_zdms,x.fd_flbm,l.fd_flmc from DSJZC_ZDMX x join DSJZC_ZDFL l on x.fd_flbm = l.fd_flbm where 1=1 and ");
        if(StringUtils.isNotBlank(fd_flbm)){
            sb.append("x.fd_flbm= '"+fd_flbm+"' and ");
        }
        sb.append("x.fd_zdmc like '%"+fd_zdmc+"%' ORDER BY x.FD_FLBM,x.FD_ZDBM");
        return iSQLQuery.query(sb.toString(),pc);
    }

    /**
     * 判断是否存在字典编码
     * @param fd_zdbm 字典编码
     * @param fd_flbm 分类编码
     * @return
     */
    @Override
    public List<Map<String, Object>> isExistzdbm(String fd_zdbm, String fd_flbm) {
        List<Map<String, Object>> list=iSQLQuery.query("SELECT * from  DSJZC_ZDMX where fd_zdbm = ? and fd_flbm = ? ",new Object[]{fd_zdbm,fd_flbm});
        return list;
    }

    /**
     * 获取所有字典分类的信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdflmc() {
        List<Map<String, Object>> res=iSQLQuery.query("SELECT * from  DSJZC_ZDFL");
        return res;
    }

    /**
     * 判断分类编码是否存在
     * @param fd_flbm 分类编码
     * @return
     */
    @Override
    public List<Map<String,Object>> isExistFlbm(String fd_flbm) {
        List<Map<String,Object>> res = iSQLQuery.query("SELECT * from DSJZC_ZDMX where fd_flbm='"+fd_flbm+"'");
        return res;
    }

}
