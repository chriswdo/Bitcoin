package com.eshore.datasupport.metadata.service.impl;

import com.eshore.datasupport.metadata.dao.IZtDao;
import com.eshore.datasupport.metadata.pojo.Zt;
import com.eshore.datasupport.metadata.service.IZtService;
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
 * Created by lsl on 2017/4/21.
 * 主题定义
 */
@Service
@Transactional
public class ZtServiceImpl extends BaseServiceImpl<Zt> implements IZtService{
    @Autowired
    IZtDao iZtDao;

    private  List<Map<String, Object>>  relist;

    @Override
    public IBaseDao<Zt> getDao() {
        return (IBaseDao<Zt>)iZtDao;
    }

    /**
     * 获取所有主题的信息
     * @param pc 分页
     * @return
     */
    @Override
    public List<Map<String,Object>> getAllZtdata(PageConfig pc) {
        relist=iZtDao.getAllZtdata(pc);
        return relist;
    }

    /**
     * 获取含有特定"主题名称"的主题的信息
     * @param ztmc 主题名称
     * @param pc
     * @return
     */
    @Override
    public List<Map<String,Object>> getZtdataByztmc(String ztmc, PageConfig pc) {
        relist=iZtDao.getZtdataByztmc(ztmc, pc);
        return relist;
    }

    /**
     * 新增主题
     * @param fd_ztmc 主题名称
     * @param fd_zt 状态
     * @param fd_ms 描述
     * @param fd_cjr 创建人
     */
    @Override
    public void addNewZt(String fd_ztmc, String fd_zt, String fd_ms,String fd_cjr) {
        String id1 = UUID.randomUUID().toString().replaceAll("-", "").trim();
        Timestamp fd_cjsj = new Timestamp(System.currentTimeMillis());
        Zt zt = new Zt();
        zt.setId(id1);
        zt.setFd_ztmc(fd_ztmc);
        zt.setFd_zt(fd_zt);
        zt.setFd_ms(fd_ms);
        zt.setFd_cjsj(fd_cjsj);
        zt.setFd_cjr(fd_cjr);
        iZtDao.addNewZt(zt);
    }

    /**
     * 由id获取单个主题信息
     * @param id
     * @return
     */
    @Override
    public Zt getZtdata(String id) {
        return iZtDao.getZtdata(id);
    }

    /**
     * 更新单个主题信息
     * @param id 主键Id
     * @param fd_ztmc 主题名称
     * @param fd_zt 状态
     * @param fd_ms 描述
     * @param fd_gxr 更新人
     */
    @Override
    public void updateZt(String id,String fd_ztmc,String fd_zt,String fd_ms,String fd_gxr) {
        Timestamp fd_gxsj = new Timestamp(System.currentTimeMillis());
        Zt zt = iZtDao.getZtdata(id);
        zt.setFd_ztmc(fd_ztmc);
        zt.setFd_zt(fd_zt);
        zt.setFd_ms(fd_ms);
        zt.setFd_gxsj(fd_gxsj);
        zt.setFd_gxr(fd_gxr);
        iZtDao.update(zt);
    }

    /**
     * 删除主题
     * @param id
     */
    @Override
    public void deleteZt(String id) {
        this.delete(id);
    }
}
