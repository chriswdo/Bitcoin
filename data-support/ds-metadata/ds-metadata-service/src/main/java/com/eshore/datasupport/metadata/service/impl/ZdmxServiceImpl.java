package com.eshore.datasupport.metadata.service.impl;

import com.eshore.datasupport.metadata.dao.IZdmxDao;
import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.datasupport.metadata.pojo.Zdmx;
import com.eshore.datasupport.metadata.service.IZdmxService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lsl on 2017/5/10.
 */
@Service
@Transactional
public class ZdmxServiceImpl extends BaseServiceImpl<Zdmx> implements IZdmxService{
    @Autowired
    IZdmxDao iZdmxDao;

    @Override
    public IBaseDao<Zdmx> getDao() {
        return (IBaseDao<Zdmx>)iZdmxDao;
    }

    /**
     * 获取所有字典明细信息
     * @param pc 分页
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdmxdata(PageConfig pc) {
        List<Map<String, Object>>  relist=iZdmxDao.getAllZdmxdata(pc);
        return relist;
    }

    /**
     * 通过分类编码，字典名称获取符合条件的字典明细
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param pc 分页
     * @return
     */
    @Override
    public List<Map<String, Object>> getZdmxdataByzdmc(String fd_flbm, String fd_zdmc, PageConfig pc) {
        List<Map<String, Object>>  relist=iZdmxDao.getZdmxdataByzdmc(fd_flbm, fd_zdmc, pc);
        return relist;
    }

    /**
     * 删除字典明细
     * @param id
     */
    @Override
    public void deleteZdmx(String id) {
        this.delete(id);
    }

    /**
     * 批量删除字典明细
     * @param list
     */
    @Override
    public void deleteMulZdmx(String list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        for (int i = 0; i < jsonArray.size(); i++){
            String s = (String) jsonArray.get(i);
            this.delete(s);
        }
    }

    /**
     * 更新字典明细
     * @param id 主键id
     * @param fd_zdmc 字典名称
     * @param fd_gxr 更新人
     * @param fd_zdms 字典描述
     */
    @Override
    public void updateZdmx(String id, String fd_zdmc, String fd_gxr, String fd_zdms) {
        Timestamp fd_gxsj = new Timestamp(System.currentTimeMillis());
        Zdmx zdmx = this.get(id);
        zdmx.setFd_zdmc(fd_zdmc);
        zdmx.setFd_zdms(fd_zdms);
        zdmx.setFd_gxr(fd_gxr);
        zdmx.setFd_gxsj(fd_gxsj);
        this.update(zdmx);
    }

    /**
     * 保存字典明细
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param fd_zdbm 字典编码
     * @param fd_cjr 创建人
     * @param fd_zdms 字典描述
     */
    @Override
    public void saveZdmx(String fd_flbm, String fd_zdmc, String fd_zdbm, String fd_cjr, String fd_zdms) {
        String id = UUID.randomUUID().toString().replaceAll("-", "").trim();
        Timestamp fd_cjsj = new Timestamp(System.currentTimeMillis());
        Zdmx zdmx = new Zdmx();
        zdmx.setId(id);
        zdmx.setFd_zdmc(fd_zdmc);
        zdmx.setFd_zdms(fd_zdms);
        zdmx.setFd_zdbm(fd_zdbm);
        zdmx.setFd_cjr(fd_cjr);
        zdmx.setFd_cjsj(fd_cjsj);
        zdmx.setFd_flbm(fd_flbm);
        this.save(zdmx);
    }

    /**
     * 判断是否存在字典编码
     * @param fd_zdbm
     * @param fd_flbm
     * @return
     */
    @Override
    public boolean isExistzdbm(String fd_zdbm, String fd_flbm) {
        List list = iZdmxDao.isExistzdbm(fd_zdbm, fd_flbm);
        if(list == null||list.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 获取所有字典分类的信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdflmc() {
        List<Map<String, Object>>  relist = iZdmxDao.getAllZdflmc();
        return relist;
    }

    /**
     * 判断是否存在该分类编码，字典分类删除时使用
     * @param fd_flbm 分类编码
     * @return
     */
    @Override
    public boolean isExistFlbm(String fd_flbm) {
        List<Map<String, Object>> relist = iZdmxDao.isExistFlbm(fd_flbm);
        if(relist == null||relist.isEmpty()){
            return false;
        }
        return true;
    }


}
