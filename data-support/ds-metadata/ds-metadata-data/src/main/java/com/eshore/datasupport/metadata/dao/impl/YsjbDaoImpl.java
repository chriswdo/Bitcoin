package com.eshore.datasupport.metadata.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eshore.datasupport.metadata.vo.DataSourceTs;
import org.apache.log4j.Logger;
import org.pentaho.di.core.encryption.KettleTwoWayPasswordEncoder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.common.vo.YsjbDescribeVo;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;


@Repository
public class YsjbDaoImpl extends JpaDaoImpl<Ysjb> implements IYsjbDao {
    private static final Logger logger = Logger.getLogger(YsjbDaoImpl.class);

    @Override
    /**
     * 查询元数据表数据
     */
    public List<Ysjb> getmetedataTable(String sjy, PageConfig pc) {
        String sql = "from  Ysjb  where FD_SJY_ID=?";
        List<Ysjb> relist =this.queryPage(sql,pc , new Object[]{sjy});
        logger.info("getmetedataTable:" + relist);
        return relist;
    }

    @Override
    /**
     * 通过表名查询元数据表
     */
    public List<Ysjb> getysjbidByname(String name, String sjy) {
        List<Ysjb> relist = new ArrayList<Ysjb>();
        try {
            String sql = " from  Ysjb  where FD_BM=? and FD_SJY_ID=? ";
            PageConfig pc=new PageConfig();
            pc.setPageSize(Integer.MAX_VALUE);
            relist =this.queryPage(sql, pc, new Object[]{name, sjy});
        } catch (Exception e) {
            logger.info(e);
        }
        return relist;
    }
    
/*    @Override
    *//**
     * 查询数据源表
     *//*
    public List<Map<String, Object>> getDatayuan() {
        List<Map<String, Object>> res = iSQLQuery.query("SELECT * from  dsjzc_sjyb");
        return res;
    }*/

    @Override
    public List<Map<String, Object>> findAnyBysjyId(String sjy_id) {
        List<Map<String, Object>> list = iSQLQuery.query("SELECT * from  dsjzc_ysjb where fd_sjy_id = ? ", new Object[]{sjy_id});
        return list;
    }

    @Override
    /**
     * 通过jdbc连接远程查询表描述信息
     */
    public List<YsjbDescribeVo> getbiaoSql(String drivename, String url, Sjyb sjyb, String bm) {
        List<YsjbDescribeVo> res = new ArrayList<YsjbDescribeVo>();
        Connection conn = null;
        try {
            Class.forName(drivename);// 动态加载mysql驱动

            KettleTwoWayPasswordEncoder tp = new KettleTwoWayPasswordEncoder();
            String password = tp.decode(sjyb.getFd_mm());
            conn = DriverManager.getConnection(url, sjyb.getFd_yhm(), password);
            res = DataSourceUtil.getysjbMeg(drivename, bm, conn);
        } catch (SQLException e) {
            logger.info(e);
        } catch (Exception e1) {
            logger.info(e1);
        } finally {

            try {
                if(conn!= null){
                    conn.close();
                }
            } catch (SQLException e) {
                logger.info(e);
            }
        }
        return res;
    }

    @Override
    public String getTableComment(String fd_sjy_id, String bm) {
        List<Map<String,Object>> res = iSQLQuery.query("SELECT * from DSJZC_YSJB where fd_sjy_id ='"+fd_sjy_id+"'and fd_bm ='"+bm+"'");
        if (res.isEmpty()){
            return null;
        }
        return res.get(0).get("fd_ms").toString();
    }

    @Override
    public String getTableId(String fd_bm, String fd_ms) {
        List<Map<String,Object>> res = iSQLQuery.query("SELECT * from DSJZC_YSJB where fd_bm ='"+fd_bm+"'and fd_ms ='"+fd_ms+"'");
        if (res.isEmpty()){
            return null;
        }
        return res.get(0).get("id").toString();
    }

}