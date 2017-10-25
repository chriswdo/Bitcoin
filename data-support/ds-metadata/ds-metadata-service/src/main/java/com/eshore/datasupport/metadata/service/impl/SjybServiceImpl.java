package com.eshore.datasupport.metadata.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.eshore.datasupport.metadata.dao.impl.YsjbDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.metadata.dao.IR_databaseDao;
import com.eshore.datasupport.metadata.dao.IR_database_attributeDao;
import com.eshore.datasupport.metadata.dao.ISjybDao;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.pojo.R_database;
import com.eshore.datasupport.metadata.pojo.R_database_attribute;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.metadata.service.util.SjybKettleDbConvertUtil;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * ���Դ��ҵ��ӿ�ʵ��
 *
 * @author
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class SjybServiceImpl extends BaseServiceImpl<Sjyb> implements ISjybService {
    private static final Logger logger = Logger.getLogger(SjybServiceImpl.class);

    @Autowired
    ISjybDao sjybDao;

    @Autowired
    IYsjbDao ysjbDao;

    @Autowired
    IR_databaseDao r_databaseDao;

    @Autowired
    IR_database_attributeDao r_database_attributeDao;

    @Override
    public IBaseDao<Sjyb> getDao() {
        return (IBaseDao<Sjyb>) sjybDao;
    }

    @Override
    public String checkConnecting(Sjyb sjybRecord) {
        String returnMsg = "";
        //数据库名称
        String dbName = sjybRecord.getFd_sjklx();
        if (dbName == null) {
            returnMsg = "db is not supported!";
            return returnMsg;
        }
        //获取drivername和url
        String driverName = DataSourceUtil.getDriverName(dbName);
        String url = DataSourceUtil.getUrl(dbName, sjybRecord.getFd_ip(), sjybRecord.getFd_dk(), sjybRecord.getFd_sjkmc());
        //测试连接
        returnMsg = getConnecting(driverName, url, sjybRecord);
        return returnMsg;
    }


    private String getConnecting(String driverName, String url, Sjyb sjybRecord) {
        String returnMsg = "success";
        //测试连接
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, sjybRecord.getFd_yhm(), sjybRecord.getFd_mm());
            if (conn == null) returnMsg = "connection failed!";
        } catch (ClassNotFoundException e) {
            logger.info(e);
            returnMsg = e.getMessage();
        } catch (SQLException e) {
            logger.info(e);
            returnMsg = e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.info(e);
                    returnMsg = e.getMessage();
                }
            }
        }
        return returnMsg;
    }

    @Override
    public synchronized String saveSjybRecord(Sjyb sjyb) {
        //获取R_database记录
        R_database database = SjybKettleDbConvertUtil.getRDatabase(sjyb, new R_database());
        //获取type类型
        r_databaseDao.setDatabase_type(database,sjyb.getFd_sjklx());
        //设置密码
        database.setPassword(SjybKettleDbConvertUtil.encryPassword(sjyb.getFd_mm()));
        sjyb.setFd_mm(SjybKettleDbConvertUtil.encryPassword(sjyb.getFd_mm()));
        r_databaseDao.saveDatabase(database);
        //保存R_database_attribute
        List<R_database_attribute> list = SjybKettleDbConvertUtil.getDatabaseAttributes(sjyb, database);
        r_database_attributeDao.saveAttributeList(list);
        //保存sjyb
        sjyb.setFd_kid(database.getId_database());
        this.save(sjyb);
        return "success";
    }

    @Override
    public void deleteSjybAndR_databaseInfo(String id) {
        Sjyb record = this.get(id);
        R_database database = r_databaseDao.get(record.getFd_kid());
        //可直接删除的
        r_databaseDao.delete(database.getId_database());
        this.delete(id);
        //删除阿ttribute
        r_database_attributeDao.deleteByR_databaseId(database.getId_database());

    }

    @Override
    public List<Sjyb> listSjybRecord(Map<String, String> hashMap, PageConfig pc) {
        List<Sjyb> list = this.sjybDao.listSjybRecord(hashMap, pc);
        for (Sjyb sjyb : list) {
            sjyb.setFd_mm(SjybKettleDbConvertUtil.decryPassword(sjyb.getFd_mm()));
        }
        return list;
    }

    @Override
    public void updateSjybRecord(Sjyb sjybRecord) {
        //更新数据源
        Sjyb sjyb = this.get(sjybRecord.getId());
        sjyb.setFd_dk(sjybRecord.getFd_dk());
        sjyb.setFd_mc(sjybRecord.getFd_mc());
        sjyb.setFd_ip(sjybRecord.getFd_ip());
        sjyb.setFd_lx(sjyb.getFd_lx());
        sjyb.setFd_sjklx(sjybRecord.getFd_sjklx());
        sjyb.setFd_sjkmc(sjybRecord.getFd_sjkmc());
        sjyb.setFd_yhm(sjybRecord.getFd_yhm());
        sjyb.setFd_mm(sjybRecord.getFd_mm());
        sjyb.setFd_ms(sjybRecord.getFd_ms());
        
        //更新r_database
        R_database database = SjybKettleDbConvertUtil.getRDatabase(sjyb, r_databaseDao.get(sjyb.getFd_kid()));
        //设置密码
        database.setPassword(SjybKettleDbConvertUtil.encryPassword(sjyb.getFd_mm()));
        sjyb.setFd_mm(SjybKettleDbConvertUtil.encryPassword(sjyb.getFd_mm()));
        r_databaseDao.update(database);
        
        //更新attribute
        r_database_attributeDao.updatePort(database.getId_database(),sjyb.getFd_dk());
        
        //保存sjyb
        this.update(sjyb);
       
    }
}
