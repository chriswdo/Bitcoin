package com.eshore.datasupport.task.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.task.dao.IGzpzDao;
import com.eshore.datasupport.task.pojo.Gzpz;
import com.eshore.datasupport.task.vo.RjobVo;
import com.eshore.datasupport.task.vo.YsjbVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class GzpzDaoImpl extends JpaDaoImpl<Gzpz> implements IGzpzDao{
	
	
	@Value("#{configProperties['kettle.allow_case']}")
	private String caseKey;

	/*@Override
	*//**
	 * 通过规则类型查询规则配置表
	 * @param pc  分页
	 * @param lx  规则类型(0 采集 1 处理 2挖掘)
	 * @return
	 *//*

	public List<Gzpz> getRulelistdata(PageConfig pc,int lx) {
		String sql="";
		List<Gzpz>  relist=new ArrayList<Gzpz>();
		if(lx==0){
			 sql="FROM  Gzpz where fd_gzlx=0 order by fd_gzmc";
		     relist=this.queryPage(sql,pc,null);
		}else if(lx==1){
			 sql="FROM  Gzpz where fd_gzlx=1 order by fd_gzmc";
		     relist= this.queryPage(sql,pc,null);
		}else{
			 sql="FROM  Gzpz where fd_gzlx=2 order by fd_gzmc";
		     relist= this.queryPage(sql,pc,null);
		}

		return relist;
	}*/

	@Override
	/**
	 * 通过规则名称模糊查询
	 * @param pc 分页
	 * @param gzm 规则名称
	 * @param lx  规则类型
	 * @return
	 */	
	public List<Gzpz> getRulelistdataBygzmc(PageConfig pc,String gzm,int lx) {
		String sql="";
		List<Gzpz>  relist=new ArrayList<Gzpz>();
		if(lx==0){
			if("".equals(gzm)){
				 sql="FROM  Gzpz where fd_gzlx=0 order by fd_gzmc";;
				 relist= this.queryPage(sql,pc,null);
			}else{
				 sql="FROM  Gzpz   where  FD_GZMC like ?  and fd_gzlx=0  order by fd_gzmc";
			     relist=this.queryPage(sql,pc,new Object[]{"%"+gzm + "%"});
			}

		}else if(lx==1){
			if("".equals(gzm)){
				 sql="FROM  Gzpz where fd_gzlx=1 order by fd_gzmc";;
				 relist= this.queryPage(sql,pc,null);
			}else{
				 sql="FROM  Gzpz   where  FD_GZMC like ?  and fd_gzlx=1  order by fd_gzmc";
			     relist=this.queryPage(sql,pc,new Object[]{"%"+gzm + "%"});
			}

		}else{
			if("".equals(gzm)){
				 sql="FROM  Gzpz where fd_gzlx=2 order by fd_gzmc";;
				 relist= this.queryPage(sql,pc,null);
			}else{
				 sql="FROM  Gzpz   where  FD_GZMC like ?  and fd_gzlx=2  order by fd_gzmc";
			     relist=this.queryPage(sql,pc,new Object[]{"%"+gzm + "%"});
			}

		}

		return relist;
	}
	

	/**
	 * 通过ID查询规则配置表
	 */
/*	public List<Map<String, Object>> getRuledataByid(String idu) {
		String sql="SELECT * FROM  dsjzc_gzpz g ,r_job  j where g.R_JOB_ID=j.ID_JOB  and g.ID=? ";
		List<Map<String, Object>>   relist= iSQLQuery.query(sql,new Object[]{idu} );
		return relist;
	}*/
	@Override
	/**
	 * 关联数据源表和元数据表得到数据源表名
	 * @param ids 数据源ID	
	 * @param bid 元数据ID
	 * @return
	 */
	public List<YsjbVo> getsjyName(String ids, String bid) {		
		String sql="SELECT  y.FD_BM,s.FD_MC from  dsjzc_ysjb  y  INNER JOIN  dsjzc_sjyb  s ON s.ID=y.FD_SJY_ID  WHERE s.ID=?  AND y.ID=?";
		PageConfig pg=new PageConfig();
		pg.setPageSize(Integer.MAX_VALUE);
		List<YsjbVo> relist = this.iSQLQuery.query(sql, new Object[]{ids,bid}, YsjbVo.class, pg);
		return relist;
	}

	/**
	 * 查询数据源表
	 */
/*	public List<Map<String, Object>> getDatainpage() {
		String sql="SELECT * FROM  dsjzc_sjyb  ";
		List<Map<String, Object>>   relist= iSQLQuery.query(sql);
		return relist;
	}*/

/*	@Override
   *//**
    * 根据数据源ID查询元数据表
    *//*
	public List<Map<String, Object>> getSrbiao(String ids) {
		String sql="SELECT * FROM  dsjzc_ysjb where FD_SJY_ID=?";
		List<Map<String, Object>>   relist= iSQLQuery.query(sql,new Object[]{ids});
		return relist;
	}*/

	@Override
	/**
	 * 查询采集的joblist
	 */
	public List<RjobVo> getJoblist(PageConfig pc,String jobtxt,String strsql) {
		String sql="";
		StringBuilder sbd=new StringBuilder();
		List<RjobVo>   relist=new ArrayList<RjobVo>();
		if("".equals(jobtxt)){
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='collect'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}	
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(), RjobVo.class, pc);
		}else{
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='collect'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null and  j.NAME like ?";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(),new Object[]{"%"+jobtxt + "%"},RjobVo.class,pc);
		}

		return relist;
	}

	@Override
	/**
	 * 查询处理的joblist
	 */
	public List<RjobVo> getclJoblist(PageConfig pc,String jobtxt,String strsql) {
		String sql="";
		StringBuilder sbd=new StringBuilder();
		List<RjobVo>   relist=new ArrayList<RjobVo>();
		if("".equals(jobtxt)){
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='process'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null ";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}	
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(), RjobVo.class, pc);
		}else{
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='process'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null  and  j.NAME like ?";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(),new Object[]{"%"+jobtxt + "%"},RjobVo.class,pc);
		}

		return relist;
	}
		
	@Override
	/**
	 * 查询调度的joblist
	 */
	public List<RjobVo> getddJoblist(PageConfig pc,String jobtxt,String strsql) {
		String sql="";
		StringBuilder sbd=new StringBuilder();
		List<RjobVo>   relist=new ArrayList<RjobVo>();
		if("".equals(jobtxt)){
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='mining'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}	
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(), RjobVo.class, pc);
		}else{
			sql="SELECT  j.ID_JOB,j.NAME,j.DESCRIPTION   FROM  r_job j   join  r_directory  r on  r.ID_DIRECTORY=j.ID_DIRECTORY  and r.DIRECTORY_NAME='mining'  left join  dsjzc_gzpz g  on g.r_job_id=j.id_job  WHERE  g.fd_gzmc is null  and  j.NAME like ?";
			if(Conts.ZT_N.equals(caseKey)){
				sql = sql + " and  j.JOB_STATUS=2  ";
			}
			sbd.append(sql).append(strsql);
			relist= this.iSQLQuery.query(sbd.toString(),new Object[]{"%"+jobtxt + "%"},RjobVo.class,pc);
		}
		return relist;
	}
	
	@Override
	public List<Map<String,Object>> findGzpzList(Map<String,Object>params, PageConfig pc) {
		StringBuilder sql = new StringBuilder( 
				"SELECT g.id ,g.fd_gzmc,s1.fd_mc fd_srsjy_mc,s2.fd_mc fd_scsjy_mc,g.r_job_name,g.r_job_desc  "+
				"FROM   DSJZC_GZPZ g "+ 
				"JOIN   DSJZC_SJYB s1 "+
				"ON	s1.id=g.fd_srsjy_id "+
				"JOIN   DSJZC_SJYB  s2 "+
				"ON  s2.id=g.fd_scsjy_id  "+
//				"JOIN   dsjzc_ysjb y1 "+
//				"ON  g.fd_srb_id = y1.id "+
//				"JOIN   dsjzc_ysjb y2 "+
//				"ON  g.fd_scb_id = y2.id  "+
				"LEFT JOIN dsjzc_rwb rwb "+
				"ON  g.id = rwb.fd_gz_id " +
				"WHERE 1=1 AND rwb.id is null " 
				);
		List<Object>params_sql = new ArrayList<Object>();
		if(StringUtils.isNotBlank((String)params.get("fd_gzmc"))){
			sql.append(" AND g.fd_gzmc LIKE ? ");
			params_sql.add("%"+(String)params.get("fd_gzmc")+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("tb_name"))){
			sql.append(" AND (y1.fd_bm LIKE ?  OR y2.fd_bm LIKE ? )");
			params_sql.add("%"+((String)params.get("tb_name"))+"%");
			params_sql.add("%"+((String)params.get("tb_name"))+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("fd_lx"))){
			sql.append(" AND g.fd_gzlx = ? ");
			params_sql.add((String)params.get("fd_lx"));
		}
		List <Map<String,Object>>list = this.iSQLQuery.query(sql.toString(), params_sql.toArray(), pc);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findRwbList(Map<String,Object>params, PageConfig pc) {
		StringBuilder sql = new StringBuilder( 
				"SELECT r.id ,g.fd_gzmc,s1.fd_mc fd_srsjy_mc,s2.fd_mc fd_scsjy_mc,r.fd_rwms,r.fd_gxsj,r.fd_rwzt,r.fd_rwbh "+
				"FROM   DSJZC_GZPZ g  "+
				"JOIN   DSJZC_SJYB s1 "+
				"ON	s1.id=g.fd_srsjy_id "+
				"JOIN   DSJZC_SJYB  s2        "+                                                                                                        
				"ON  s2.id=g.fd_scsjy_id      "+                                                                                                         
				"JOIN   DSJZC_RWB r           "+                                                                                                         
				"ON  g.id=r.fd_gz_id          "     
				);
		List<Object>params_sql = new ArrayList<Object>();
		if(StringUtils.isNotBlank((String)params.get("fd_gzmc"))){
			sql.append(" AND g.fd_gzmc LIKE ? ");
			params_sql.add("%"+(String)params.get("fd_gzmc")+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("db_name"))){
			sql.append(" AND (s1.fd_mc LIKE ?  OR  s2.fd_mc LIKE ? )");
			params_sql.add("%"+((String)params.get("db_name"))+"%");
			params_sql.add("%"+((String)params.get("db_name"))+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("fd_lx"))){
			sql.append(" AND  r.fd_lx = ? ");
			params_sql.add((String)params.get("fd_lx"));
		}
		sql.append(" ORDER BY  r.fd_rwbh desc ");
		List <Map<String,Object>>list = this.iSQLQuery.query(sql.toString(), params_sql.toArray(), pc);
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> findRwbListForGj(Map<String,Object>params, PageConfig pc) {
		StringBuilder sql = new StringBuilder( 
				"SELECT r.id ,g.fd_gzmc,s1.fd_mc fd_srsjy_mc,s2.fd_mc fd_scsjy_mc,r.fd_rwms,r.fd_gxsj,r.fd_rwzt,r.fd_rwbh "+
				"FROM   DSJZC_GZPZ g  "+
				"JOIN   DSJZC_SJYB s1 "+
				"ON	s1.id=g.fd_srsjy_id "+
				"JOIN   DSJZC_SJYB  s2        "+                                                                                                        
				"ON  s2.id=g.fd_scsjy_id      "+                                                                                                         
				"JOIN   DSJZC_RWB r           "+                                                                                                         
				"ON  g.id=r.fd_gz_id          "+
				"LEFT JOIN   DSJZC_GJ gj      "+
				"ON  r.id=gj.fd_rwid          "+
				"WHERE gj.fd_rwid is  null    "
				);
		List<Object>params_sql = new ArrayList<Object>();
		if(StringUtils.isNotBlank((String)params.get("fd_gzmc"))){
			sql.append(" AND g.fd_gzmc LIKE ? ");
			params_sql.add("%"+(String)params.get("fd_gzmc")+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("db_name"))){
			sql.append(" AND (s1.fd_mc LIKE ?  OR  s2.fd_mc LIKE ? )");
			params_sql.add("%"+((String)params.get("db_name"))+"%");
			params_sql.add("%"+((String)params.get("db_name"))+"%");
		}
		if(StringUtils.isNotBlank((String)params.get("fd_lx"))){
			sql.append(" AND  r.fd_lx = ? ");
			params_sql.add((String)params.get("fd_lx"));
		}
		sql.append(" ORDER BY  r.fd_rwbh desc ");
		List <Map<String,Object>>list = this.iSQLQuery.query(sql.toString(), params_sql.toArray(), pc);
		return list;
	}

	@Override
	/**
	 * 根据规则名称和类型查询规则配置表
	 */
	public List<Gzpz> getgzlistBy(String names,Integer lx) {
		String sql="from Gzpz  where fd_gzmc=?  and  fd_gzlx=?";
		PageConfig pc=new PageConfig();
		pc.setPageSize(Integer.MAX_VALUE);
		List<Gzpz> relist=this.queryPage(sql,pc,new Object[]{names,lx});
		return relist;
	}


}