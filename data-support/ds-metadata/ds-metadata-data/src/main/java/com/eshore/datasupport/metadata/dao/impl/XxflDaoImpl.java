package com.eshore.datasupport.metadata.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.dao.IXxflDao;
import com.eshore.datasupport.metadata.pojo.Xxfl;
import com.eshore.datasupport.metadata.vo.MessageVo;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;


@Repository
public class XxflDaoImpl extends JpaDaoImpl<Xxfl> implements IXxflDao {
    private static final Logger logger = Logger.getLogger(XxflDaoImpl.class);

    @Override
    /**
     * 通过信息名称查询信息分类表(名称为空查询全部)
     */
    public List<MessageVo> getMessagedata(String zt, String meg, PageConfig pc) {
        String sql = "";
        List<MessageVo> relist = null;
        if ("".equals(zt) && "".equals(meg)) {
            sql = "SELECT z.FD_ZTMC,z.ID as ZID,x.FD_XXMC,x.FD_BM,x.FD_MS,x.ID  from  DSJZC_XXFL  x left join DSJZC_ZT z on x.FD_ZTID=z.ID  order by x.FD_XXMC ";
            relist =this.iSQLQuery.query(sql,MessageVo.class,pc); 

        } else if ("".equals(zt) && !"".equals(meg)) {
            sql = "SELECT z.FD_ZTMC,z.ID as ZID,x.FD_XXMC,x.FD_BM,x.FD_MS,x.ID  from  DSJZC_XXFL  x left join DSJZC_ZT z on x.FD_ZTID=z.ID  where  x.FD_XXMC  like ?  order by x.FD_XXMC ";
            relist =this.iSQLQuery.query(sql,new Object[]{"%" + meg + "%"},MessageVo.class,pc); 
        } else {
            sql = "SELECT z.FD_ZTMC,z.ID as ZID,x.FD_XXMC,x.FD_BM,x.FD_MS,x.ID  from  DSJZC_XXFL  x left join DSJZC_ZT z on x.FD_ZTID=z.ID  where  z.ID=? and x.FD_XXMC  like ? order by x.FD_XXMC  ";
            relist = this.iSQLQuery.query(sql, new Object[]{zt, "%" + meg + "%"},MessageVo.class, pc);
        }

        return relist;
    }

    @Override
    public List<Map<String, Object>> getZhutidata() {
        List<Map<String, Object>> res = iSQLQuery.query("SELECT * from  DSJZC_ZT");
        return res;
    }

/*    @Override
    *//**
     * 通过ID查询信息分类表
     *//*
    public List<Map<String, Object>> getmessageDataByid(String idd) {
        String sql = "SELECT z.FD_ZTMC,x.FD_XXMC,x.FD_BM,x.FD_MS,x.ID,z.ID as zid  from  DSJZC_XXFL  x left join DSJZC_ZT z on x.FD_ZTID=z.ID where x.ID=?  ";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{idd});
        return relist;
    }*/

    @Override
    /**
     * 通过ID和字段名称查询信息字段表
     */
    public List<YsjzdVo> getxxmetadataBymc(String megid, String mc) {
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_zdsx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid   from dsjzc_xxfl xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =?  and xd.fd_zdmc=?";
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
     * 更新信息字段表
     */
    public void updateMegdata(String xxzt, String xxmc, String xxms, String xxid, String userid, String ti) {
        String sql = "update dsjzc_xxfl set fd_ztid='" + xxzt + "',fd_xxmc='" + xxmc + "',fd_ms='" + xxms + "',fd_gxr='" + userid + "',fd_gxsj=to_date('" + ti + "','yyyy-mm-dd hh24:mi:ss')  where id='" + xxid + "'";
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
        Statement stmt =null;
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
      		  if(conn != null){
    				conn.rollback();
      		  }
            } catch (SQLException e1) {
                logger.info(e1);
            }
            logger.info(e);
            res = e.getMessage().split(":")[1];
        } finally {

            try{
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e){
                logger.info(e);
            }
            try{
                if (stmt != null) {
                    stmt.close();
                }
            }catch (Exception e){
                logger.info(e);
            }

        }
        return res;

    }

    @Override
    public List<Map<String, Object>> findAnyBysjyId(String ztid) {
        List<Map<String, Object>> list = iSQLQuery.query("SELECT * from  DSJZC_XXFL where fd_ztid = ? ", new Object[]{ztid});
        return list;
    }

    @Override
    public List<Map<String, Object>> getIDfromxxfl(String xxbm) {
        List<Map<String, Object>> relist = null;
        return relist;
    }


	@Override
	/**
	 * 添加数据到信息分类表
	 */
	public void addxxflMeg(RequestParams params,String ti,String cjr,String gxr) {
		String xxuuid = params.getXxid();
		String xxbm = params.getXxbm();
		String xxms = params.getXxms();
		String xxzt = params.getXxzt();
		String xxmc = params.getXxmc();
      	String sql="insert into  dsjzc_xxfl(id,fd_bm,fd_cjr,fd_cjsj,fd_gxr,fd_gxsj,fd_ms,fd_xxmc,fd_ztid)  values('"+xxuuid+"','"+xxbm+"','"+cjr+"',to_date('"+ti+"','yyyy-mm-dd hh24:mi:ss'),'"+gxr+"',to_date('"+ti+"','yyyy-mm-dd hh24:mi:ss'),'"+xxms+"','"+xxmc+"','"+xxzt+"')";
		 jdbcTemplate.execute(sql);
		 
	}


    @Override
    /**
     * 通过表名查询信息分类表
     */
    public List<Xxfl> getDataBybm(String xxbm) {
        String sql = " from Xxfl   where fd_bm=? ";
		PageConfig pc=new PageConfig();
		pc.setPageSize(Integer.MAX_VALUE);
        List<Xxfl> relist =this.queryPage(sql,pc,new Object[]{xxbm});
        return relist;
    }
    @Override
	/**
	 * 通过ID查询信息分类表
	 * @param megid
	 * @return
	 */
    public  List<Map<String, Object>>  getmetaDatainpage(String megid){
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdsx,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid,xd.FD_GXSJ   from dsjzc_xxfl xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =?  order by xd.FD_GXSJ desc ";
        List<Map<String, Object>> relist = iSQLQuery.query(sql, new Object[]{megid});
        return  relist;
    }
    @Override
    /**
     * 通过ID查询信息分类表,并将数据存储到pojo类中
     */
    public  List<YsjzdVo> getxxmetadata(String megid) {
        String sql = " select xf.id,xf.fd_bm,xd.fd_lx,xd.fd_qsz,xd.fd_sfkwk,xd.fd_sfzj,xd.fd_zdsx,xd.fd_zdbz,xd.fd_zdcd,xd.fd_zdlx,xd.fd_zdmc,xd.fd_zdjd,xd.id  as xid,xd.FD_GXSJ   from dsjzc_xxfl xf left join dsjzc_xxzd  xd  on xf.id=xd.fd_xx_id  where xf.id =?  order by xd.FD_GXSJ desc ";
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


}