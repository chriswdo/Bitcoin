package com.eshore.datasupport.metadata.service.impl;

import com.eshore.datasupport.metadata.dao.IZdflDao;
import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.datasupport.metadata.service.IZdflService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lsl on 2017/5/9.
 */
@Service
@Transactional
public class ZdflServiceImpl extends BaseServiceImpl<Zdfl> implements IZdflService{
    @Autowired
    IZdflDao iZdflDao;

    @Override
    public IBaseDao<Zdfl> getDao() {
        return (IBaseDao<Zdfl>)iZdflDao;
    }

    /**
     * 获取所有字典分类的信息
     * @param pc 分页
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllZdfldata(PageConfig pc) {
        List<Map<String, Object>>  relist=iZdflDao.getAllZdfldata(pc);
        return relist;
    }

    /**
     * 获取含有特定"字典分类名称"的主题的信息
     * @param fd_flmc
     * @param pc
     * @return
     */
    @Override
    public List<Map<String, Object>> getZdfldataByzdflmc(String fd_flmc, PageConfig pc) {
        List<Map<String, Object>>  relist=iZdflDao.getZdfldataByzdflmc(fd_flmc, pc);
        return relist;
    }

    /**
     * 删除单个字典分类信息
     * @param id
     */
    @Override
    public void deleteZdfl(String id) {
        this.delete(id);
    }

    /**
     * 更新字典分类
     * @param id 主键
     * @param fd_flmc 分类名称
     * @param fd_zt 状态
     * @param fd_gxr 更新人
     */
    @Override
    public void updateZdfl(String id, String fd_flmc, String fd_zt, String fd_gxr) {
        Timestamp fd_gxsj = new Timestamp(System.currentTimeMillis());
        Zdfl zdfl = this.get(id);
        zdfl.setFd_zt(fd_zt);
        zdfl.setFd_flmc(fd_flmc);
        zdfl.setFd_gxr(fd_gxr);
        zdfl.setFd_gxsj(fd_gxsj);
        this.update(zdfl);
    }

    /**
     * 保存字典分类
     * @param fd_flmc 分类名称
     * @param fd_flbm 分类编码
     * @param fd_zt 状态
     * @param fd_cjr 创建人
     */
    @Override
    public void saveZdfl(String fd_flmc, String fd_flbm,String fd_zt, String fd_cjr) {
        String id = UUID.randomUUID().toString().replaceAll("-", "").trim();
        Timestamp fd_cjsj = new Timestamp(System.currentTimeMillis());
        Zdfl zdfl = new Zdfl();
        zdfl.setId(id);
        zdfl.setFd_cjr(fd_cjr);
        zdfl.setFd_zt(fd_zt);
        zdfl.setFd_flmc(fd_flmc);
        zdfl.setFd_flbm(fd_flbm);
        zdfl.setFd_cjsj(fd_cjsj);
        this.save(zdfl);
    }

    /**
     * 是否存在分类编码
     * @param fd_flbm
     * @return
     */
    @Override
    public boolean isExistflbm(String fd_flbm) {
        List list = iZdflDao.isExistflbm(fd_flbm);
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 获取字典分类信息通过主键id
     * @param id
     * @return
     */
	@Override
	public Zdfl getZdfldataByID(String id) {
        return this.get(id);
	}

}
