package com.eshore.datasupport.metadata.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eshore.datasupport.common.vo.YsjzdVo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.metadata.dao.IMxdyDao;
import com.eshore.datasupport.metadata.pojo.Jcmxdy;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class MxdyDaoImpl extends JpaDaoImpl<Jcmxdy> implements IMxdyDao {

    private static final Logger logger = Logger.getLogger(MxdyDaoImpl.class);

    @Override
	/**
	 * 查询决策模型定义表，返回list集合数据
	 * @param zt 主题ID
	 * @param meg 模型名称
	 * @param pc  分页
	 * @return list
	 */
    public List<Jcmxdy> getmxdyMessagedata(String zt, String meg, PageConfig pc) {
		String hql = "FROM Jcmxdy WHERE 1=1 ";
		StringBuilder sbd=new StringBuilder();
		sbd.append(hql);
		List<Object>paramsSql = new ArrayList<Object>();
		if(StringUtils.isNotEmpty(meg)){
			sbd.append(" AND fd_jcmxmc LIKE ?");
			paramsSql.add("%"+meg+"%");
		}
		if(StringUtils.isNotEmpty(zt)){
			sbd.append(" AND FD_LX = ?");
			paramsSql.add(zt);
		}
		sbd.append("  order by FD_JCMXMC ");
		List<Jcmxdy> list = this.queryPage(sbd.toString(),pc , paramsSql.toArray());
        return list;
    }

    @Override
	/**
	 * 得到主题list
	 * @return
	 */
    public List<Map<String, Object>> getZhutidata() {
        List<Map<String, Object>> res = iSQLQuery.query("SELECT * from  DSJZC_ZT");
        return res;
    }

/*    @Override
	*//**
	 * 通过ID查询决策模型表
	 * @param idd
	 * @return
	 *//*
    public List<Map<String, Object>> getjcmxmessageDataByid(String idd) {
        String sql = "select * from dsjzc_jcmxdy  where ID=?  ";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{idd});
        return relist;
    }*/

    @Override
	/**
	 * 通过ID查询信息字段表
	 * @param megid  模型表ID
	 * @return
	 */
    public List<Map<String, Object>> getjcmxmetadata(String megid) {
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdsx,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid ,xd.FD_GXSJ  from dsjzc_jcmxdy xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =? order by xd.FD_GXSJ desc ";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{megid});
        return relist;
    }

    @Override
    /**
     * 更新决策模型表
     */
    public void updateMegdata(String xxzt, String xxmc, String xxms, String xxid, String userid, String ti) {
        String sql = "update dsjzc_jcmxdy set fd_lx='" + xxzt + "',fd_jcmxmc='" + xxmc + "',fd_ms='" + xxms + "',fd_gxr='" + userid + "',fd_gxsj=to_date('" + ti + "','yyyy-mm-dd hh24:mi:ss')  where id='" + xxid + "'";
        logger.info(sql);
        super.jdbcTemplate.execute(sql);
    }

    @Override
	/**
	 * 通过jdbc连接数据库，执行sql语句
	 * @param url  jdbc连接地址
	 * @param driveclass jdbc驱动
	 * @param sqls  执行sql 
	 * @param username jdbc连接用户名
	 * @param password  jdbc连接密码
	 * @return
	 */
    public String editxxmetadata(String url, String driveclass, List<String> sqls, String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        String res = "";
        try {
            Class.forName(driveclass);// 动态加载mysql驱动
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (String str : sqls) {
                stmt.execute(str);
            }
            conn.commit();
            res = "ok";
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                logger.info(e1);
            }

            logger.info(e);
            res = e.getMessage().split(":")[1];
        } finally {
            stmtClose(stmt);
            connClose(conn);
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getIDfromxxfl(String xxbm) {
        List<Map<String, Object>> relist = null;
        return relist;
    }


    @Override
    /**
     * 添加数据到模型定义表
     */
    public void addxxflMeg(RequestParams params, String ti, String cjr, String gxr) {
        String xxuuid = params.getXxid();
        String xxbm = params.getXxbm();
        String xxms = params.getXxms();
        String xxzt = params.getXxzt();
        String xxmc = params.getXxmc();
        String sql = "insert into  dsjzc_jcmxdy(id,fd_bm,fd_cjr,fd_cjsj,fd_gxr,fd_gxsj,fd_jcmxmc,fd_lx,fd_ms)  values('" + xxuuid + "','" + xxbm + "','" + cjr + "',to_date('" + ti + "','yyyy-mm-dd hh24:mi:ss'),'" + gxr + "',to_date('" + ti + "','yyyy-mm-dd hh24:mi:ss'),'" + xxmc + "','" + xxzt + "','" + xxms + "')";
        jdbcTemplate.execute(sql);

    }


    @Override
    public List<Jcmxdy> getDataBybm(String xxbm) {
        String sql = "from Jcmxdy   where fd_bm=? ";
        PageConfig pc=new PageConfig();
        pc.setPageSize(Integer.MAX_VALUE);
        List<Jcmxdy> relist = this.queryPage(sql, pc, new Object[]{xxbm});
        return relist;
    }

    @Override
    /**
     * 得到模型定义数据通过ID和字段名称
     */
    public List<YsjzdVo> getmxdymetadataBymc(String megid, String mc) {
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdsx,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid   from dsjzc_jcmxdy xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =?   and xd.fd_zdmc=?";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{megid, mc});
        List<YsjzdVo> resultlist = new ArrayList<YsjzdVo>();
        for (Map<String, Object> map : relist) {
            YsjzdVo ysjzdvo = new YsjzdVo();
            ysjzdvo.setId((String) map.get("XID"));
            ysjzdvo.setQsz((String) map.get("FD_QSZ"));
            ysjzdvo.setSfkwk((String) map.get("FD_SFKWK"));
            ysjzdvo.setSfzj((String) map.get("FD_SFZJ"));
            ysjzdvo.setZdbz((String) map.get("FD_ZDBZ"));
            if (map.get("FD_ZDCD") != null) {
                ysjzdvo.setZdcd(Short.valueOf(map.get("FD_ZDCD").toString()));
            }
            ysjzdvo.setZdlx((String) map.get("FD_ZDLX"));
            ysjzdvo.setZdmc((String) map.get("FD_ZDMC"));
            if (map.get("FD_ZDSX") != null) {
                ysjzdvo.setZdsx(Short.valueOf(map.get("FD_ZDSX").toString()));
            }

            resultlist.add(ysjzdvo);
        }
        return resultlist;
    }

	@Override
	/**
	 * 通过ID查询决策模型表,封装成pojo类
	 */
	public List<YsjzdVo> getmxdymetadataForcompare(String megid) {
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdsx,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid ,xd.FD_GXSJ  from dsjzc_jcmxdy xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =? order by xd.FD_GXSJ desc ";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{megid});
        List<YsjzdVo> resultlist = new ArrayList<YsjzdVo>();
        for (Map<String, Object> map : relist) {
            YsjzdVo ysjzdvo = new YsjzdVo();
            ysjzdvo.setId((String) map.get("XID"));
            ysjzdvo.setQsz((String) map.get("FD_QSZ"));
            ysjzdvo.setSfkwk((String) map.get("FD_SFKWK"));
            ysjzdvo.setSfzj((String) map.get("FD_SFZJ"));
            ysjzdvo.setZdbz((String) map.get("FD_ZDBZ"));
            if (map.get("FD_ZDCD") != null) {
                ysjzdvo.setZdcd(Short.valueOf(map.get("FD_ZDCD").toString()));
            }
            ysjzdvo.setZdlx((String) map.get("FD_ZDLX"));
            ysjzdvo.setZdmc((String) map.get("FD_ZDMC"));
            if (map.get("FD_ZDSX") != null) {
                ysjzdvo.setZdsx(Short.valueOf(map.get("FD_ZDSX").toString()));
            }
            resultlist.add(ysjzdvo);
        }
        return resultlist;
	}

    /**
     * 关闭连接
     * @param stmt
     */
    private static void stmtClose(Statement stmt){
        try{
            if (stmt != null) {
                stmt.close();
            }
        }catch (Exception e){
            logger.info(e);
        }
    }

    /**
     * 关闭连接
     * @param conn
     */
    private static void  connClose(Connection conn){
        try{
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e){
            logger.info(e);
        }
    }

}