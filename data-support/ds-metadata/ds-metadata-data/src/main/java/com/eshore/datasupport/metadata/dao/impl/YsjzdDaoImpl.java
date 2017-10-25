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
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.dao.IYsjzdDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.pojo.Ysjzd;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class YsjzdDaoImpl extends JpaDaoImpl<Ysjzd> implements IYsjzdDao {
    private final Logger logger = Logger.getLogger(YsjzdDaoImpl.class);

    @Override
    public List<Map<String, Object>> editysjzdData(String parm1, String parm2) {
        String sql = "select * from dsjzc_ysjb t1 left join dsjzc_ysjzd t2 on t1.id =t2.FD_YSJB_ID  where t2.ID=?  and  t2.FD_YSJB_ID=?";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{parm1, parm2});
        return relist;
    }

    @Override
    public int saveysjzdData(String pa1, String pa2) {

        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * 通过表名查询元数据字段
     */
    public List<Ysjzd> getysjzdData(String bm) {
        String sql = "from Ysjzd   where FD_YSJB_ID=?";
        PageConfig pc=new PageConfig();
        pc.setPageSize(Integer.MAX_VALUE);
        List<Ysjzd> relist =this.queryPage(sql, pc, new Object[]{bm});
        logger.info(".......getysjzdData");
        return relist;
    }

    @Override
    /**
     * 通过jdbc远程查找表的字段信息
     */
    public List<YsjzdVo> getdatayuanByid(String drivename, String url, Sjyb sjyb, String bm) {
        List<YsjzdVo> res = new ArrayList<YsjzdVo>();
        Connection conn = null;
        try {

            Class.forName(drivename);// 动态加载mysql驱动
            KettleTwoWayPasswordEncoder tp = new KettleTwoWayPasswordEncoder();
            String password = tp.decode(sjyb.getFd_mm());
            conn = DriverManager.getConnection(url, sjyb.getFd_yhm(), password);
            res = DataSourceUtil.getMetadatacolm(drivename, bm, conn);

        } catch (SQLException e) {
            logger.info(e + "====" + "sql操作错误");
        } catch (Exception e1) {
            logger.info(e1);
        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

                logger.info(e);
            }

        }

        return res;
    }

/*    @Override
    *//**
     * 通过表名查询元数据表
     *//*
    public List<Map<String, Object>> getysjbidByname(String name, String sjy) {
        List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
        try {
            String sql = "SELECT * from  dsjzc_ysjb  where FD_BM=? and FD_SJY_ID=? ";
            relist = iSQLQuery.query(sql, new Object[]{name, sjy});
        } catch (Exception e) {
            logger.info(e);
        }

        return relist;
    }*/

    @Override
    /**
     * 通过ID和表名查询元数据字段表，并封装成pojo类
     */
    public List<YsjzdVo> getysjzdDataBybm(String id, String bm, String sjy) {
        String sql = "select y.ID,y.FD_CJR,y.FD_CJSJ,y.FD_GXR,y.FD_GXSJ,y.FD_QSZ,y.FD_SFKWK,y.FD_SFZJ,y.FD_SJZD,y.FD_YSJB_ID,y.FD_ZDBZ, y.FD_ZDCD,y.FD_ZDJD,y.FD_ZDLX,y.FD_ZDSX,y.FD_ZDMC  from dsjzc_ysjzd y,dsjzc_ysjb b  where  y.fd_ysjb_id=b.id and y.fd_ysjb_id=?  and y.fd_zdmc=?   and  b.fd_sjy_id=?";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{id, bm, sjy});
        List<YsjzdVo> resultlist = new ArrayList<YsjzdVo>();
        for (Map<String, Object> map : relist) {
            YsjzdVo ysjzdvo = new YsjzdVo();

            ysjzdvo.setId((String) map.get("ID"));
            ysjzdvo.setQsz((String) map.get("FD_QSZ"));
            ysjzdvo.setSfkwk((String) map.get("FD_SFKWK"));
            ysjzdvo.setSfzj((String) map.get("FD_SFZJ"));
            ysjzdvo.setZdbz((String) map.get("FD_ZDBZ"));
            ysjzdvo.setZdcd(Short.valueOf(map.get("FD_ZDCD").toString()));
            ysjzdvo.setZdjd(map.get("FD_ZDJD")==null?0:Short.valueOf(map.get("FD_ZDJD").toString()));
            ysjzdvo.setZdlx((String) map.get("FD_ZDLX"));
            ysjzdvo.setZdmc((String) map.get("FD_ZDMC"));
            ysjzdvo.setZdsx(Short.valueOf(map.get("FD_ZDSX").toString()));
            resultlist.add(ysjzdvo);
        }
        logger.info(".......getysjzdDataBybm");
        return resultlist;
    }

    class dataSourceTsMap implements RowMapper<DataSourceTs> {
        @Override
        public DataSourceTs mapRow(ResultSet resultSet, int i) throws SQLException {
            DataSourceTs dataSourceTs = new DataSourceTs();
            dataSourceTs.setFieldName(resultSet.getString("fd_zdmc"));
            dataSourceTs.setDataType(resultSet.getString("fd_zdlx"));
            dataSourceTs.setDataLength(resultSet.getShort("fd_zdcd"));
            dataSourceTs.setDataPrecision(resultSet.getShort("fd_zdjd"));
            dataSourceTs.setPrimaryKey(resultSet.getString("fd_sfzj"));
            dataSourceTs.setDefaultValue(resultSet.getString("fd_qsz"));
            dataSourceTs.setNullAble(resultSet.getString("fd_sfkwk"));
            dataSourceTs.setFieldComment(resultSet.getString("fd_zdbz"));
            return dataSourceTs;
        }
    }

    @Override
    public List<DataSourceTs> getDataSourceTs(String id){
        List<Object> params = new ArrayList<>();
        String sql = "select * from dsjzc_ysjzd where FD_YSJB_ID=?";
        params.add(id);
        List<DataSourceTs> list= this.jdbcTemplate.query(sql.toString(),
                params.toArray(),new dataSourceTsMap());
        if(list!=null && !list.isEmpty()){
            return list;
        }
        return null;
    }
}