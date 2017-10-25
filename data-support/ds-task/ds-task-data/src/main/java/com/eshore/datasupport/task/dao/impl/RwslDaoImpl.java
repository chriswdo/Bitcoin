package com.eshore.datasupport.task.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.task.dao.IRwslDao;
import com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.datasupport.task.vo.ExceptionRecordVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class RwslDaoImpl extends JpaDaoImpl<Rwsl> implements IRwslDao{
	@Value("#{configProperties['jdbc.driverClassName']}")
	private String db;
	
	private final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = Logger.getLogger(RwslDaoImpl.class);
	
	@Override
	public Rwsl findLastRwsl(String dsJobId,Date sjc){
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder("SELECT id,fd_bzxx,fd_cljls,fd_jssj,fd_qdsj,fd_rw_id,fd_sjc,fd_sjzt,fd_ycjls,fd_yxcs,fd_zxjg ");
		 
		sql.append(" FROM dsjzc_rwsl a ");
		 
		sql.append("WHERE a.FD_ZXJG='1' ");//
		if(StringUtils.isNotEmpty(dsJobId)){			
			sql.append(" and a.FD_RW_ID = ? ");	//任务id
			params.add(dsJobId);
		}
		if(sjc!=null){
			sql.append(" and a.FD_SJC= ? ");	//任务id
			params.add(sjc);
		}
		
		sql.append(" order by a.FD_JSSJ desc");
		logger.info(sql);
		logger.info(dsJobId);
		logger.info(sjc);
		List<Rwsl> list= this.jdbcTemplate.query(sql.toString(),
				params.toArray(),new TaskKettleSlMap());
		if(list!=null&& !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	static class TaskKettleSlMap implements RowMapper<Rwsl> {
		@Override
		public Rwsl mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Rwsl vo = new Rwsl();	
			vo.setId(rs.getString("id"));			 
						
			vo.setFd_bzxx(rs.getString("fd_bzxx").getBytes());// 
			vo.setFd_cljls(rs.getLong("fd_cljls"));// 
			vo.setFd_jssj(rs.getDate("fd_jssj"));// 
			vo.setFd_qdsj(rs.getDate("fd_qdsj"));//
			vo.setFd_rw_id(rs.getString("fd_rw_id"));// 
			vo.setFd_sjc(rs.getDate("fd_sjc"));// 
			vo.setFd_sjzt(rs.getString("fd_sjzt"));// 
			 
			vo.setFd_ycjls(rs.getLong("fd_ycjls"));// 
			
			 
			vo.setFd_yxcs(rs.getString("fd_yxcs"));// 
			vo.setFd_zxjg(rs.getInt("fd_zxjg"));// 
			 
			return vo;
		}
	}
	
	
	@Override
	public void saveRWSL(Rwsl rwsl){
		if(StringUtils.isNotBlank(rwsl.getId())){
			String sql="insert into dsjzc_rwsl(id,fd_rw_id,fd_qdsj, fd_jssj, fd_yxcs, fd_zxjg, fd_cljls, fd_ycjls, fd_bzxx, fd_sjzt, fd_sjc) "
					+ "VALUES ('"+rwsl.getId()+"','"+ rwsl.getFd_rw_id() +"',"+ DataSourceUtil.getDateSqlString(db, "'"+ymdhms.format(rwsl.getFd_qdsj())+"'", "0") 
					+","+ DataSourceUtil.getDateSqlString(db, "'"+ymdhms.format(rwsl.getFd_jssj())+"'", "0") +",'"+ rwsl.getFd_yxcs() +"',"+rwsl.getFd_zxjg()
					+","+rwsl.getFd_cljls()+","+ rwsl.getFd_ycjls() +",'"+rwsl.getFd_bzxx().toString() +"','Y',"+DataSourceUtil.getDateSqlString(db, "'"+ymdhms.format(rwsl.getFd_sjc())+"'", "0")+")";
			super.jdbcTemplate.execute(sql);
		}
	}
	@Override
	/**
	 * 查询日志运行的结果数据
	 * @param gzm
	 * @param pc
	 * @param lx
	 * @return
	 */
	public List<Map<String, Object>> getRulemonitordata(String gzm,PageConfig pc,int lx) {
		String sql="";
		List<Map<String, Object>> relist;
		if(gzm.isEmpty()){
			if(lx==0){
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where t.fd_lx=0   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc order by g.fd_gzmc";		
			}else if(lx==1){
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where t.fd_lx=1   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc  order by g.fd_gzmc";		
			}else{
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where t.fd_lx=2   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc order by g.fd_gzmc ";		
				 	
			}
			relist= iSQLQuery.query(sql,pc);
		}else{
			if(lx==0){
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where g.fd_gzmc like ?  and  t.fd_lx=0   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc  order by g.fd_gzmc";		
			}else if(lx==1){
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where g.fd_gzmc like ?  and  t.fd_lx=1   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc  order by g.fd_gzmc";		
			}else{
				sql=" select count(r.fd_rw_id) as count,sum(fd_cljls) as sumcount,t.id,t.fd_gz_id,g.fd_gzmc,g.r_job_name from dsjzc_rwb t left join dsjzc_rwsl r on t.id=r.fd_rw_id   left join dsjzc_gzpz g  on g.id=t.fd_gz_id  where g.fd_gzmc like ?  and  t.fd_lx=2   group by t.id,t.fd_gz_id,g.r_job_name,g.fd_gzmc  order by g.fd_gzmc";		
			}
			 relist= iSQLQuery.query(sql,new Object[]{"%"+gzm + "%"},pc);		
		}			
		
		return relist;
	}
	@Override
	/**
	 * 通过任务实例表查询任务运行的成功次数
	 * @param idd
	 * @return
	 */
	public Map getWorkcount(String idd) {
		Map  res=new HashMap<>();
		String sql="select count(1)  as oktime,sum(fd_cljls) as datacount  from dsjzc_rwsl where fd_rw_id=?  and  fd_zxjg=1 ";	
		String sql2="select * from ( select * from dsjzc_rwsl order by  fd_qdsj  desc ) where  fd_rw_id=? and rownum <= 1 ";
		List<Map<String, Object>> relist= iSQLQuery.query(sql,new Object[]{idd});
		List<Map<String, Object>> comlist= iSQLQuery.query(sql2,new Object[]{idd});
		res.put("suclist", relist);
		res.put("tilist", comlist);
		return res;
	}
	@Override
	/**
	 * 通过ID查询任务实例表
	 * @param idd
	 * @param pc
	 * @return
	 */
	public List<Rwsl> getRuledetail(String idd,PageConfig pc) {
		String sql="";
		List<Rwsl> relist;
		if(idd.isEmpty()){
			sql="from Rwsl  order by fd_qdsj desc ";	
			 relist= this.queryPage(sql, pc, null);
		}else{
			sql="from Rwsl where fd_rw_id=?  order by fd_qdsj desc ";	
			relist= this.queryPage(sql, pc, new Object[]{idd});
		}
		   	
		return relist;
	}
/*	@Override
	*//**
	 * 通过排序得到任务的运行时间
	 * @param idd
	 * @return
	 *//*
	public List<Map<String, Object>> getRuledetailtime(String ids) {
		String sql="";
		List<Map<String, Object>> relist;
		if(ids.isEmpty()){
			sql="select  *  from dsjzc_rwsl  order by fd_qdsj desc ";	
			 relist= iSQLQuery.query(sql);	
		}else{
			sql="select  *  from dsjzc_rwsl where fd_rw_id=?  order by fd_qdsj desc ";	
			relist= iSQLQuery.query(sql,new Object[]{ids});	
		}
		   	
		return relist;
	}*/
	@Override
	/**
	 * 查询异常记录表
	 */
	public List<ExceptionRecordVo> getYcjudetail(String idd, PageConfig pc) {
		String sql="";
		List<ExceptionRecordVo> relist;
		if(idd.isEmpty()){
			 sql="select  t.fd_bz,t.id,t.fd_srb_id,t.fd_srsjy_id,r.fd_jssj   from dsjzc_ycjl t inner join dsjzc_rwsl r on r.id=t.id and t.fd_rw_id=r.fd_rw_id  order by r.fd_jssj";	
			 relist= this.iSQLQuery.query(sql, null, ExceptionRecordVo.class, pc);
		}else{
			sql="select t.fd_bz,t.id,t.fd_srb_id,t.fd_srsjy_id,r.fd_jssj  from dsjzc_ycjl t inner join dsjzc_rwsl r on r.id=t.id and t.fd_rw_id=r.fd_rw_id  where t.fd_rw_id=? order by r.fd_jssj";	
			relist= this.iSQLQuery.query(sql, new Object[]{idd}, ExceptionRecordVo.class, pc);
		}
		return relist;
	}
	
/*	@Override
	public List<Map<String, Object>> getYcjuname(String ids) {
		String sql="select y.id, y.fd_bm,s.fd_mc  from dsjzc_ysjb  y left join dsjzc_sjyb s on y.fd_sjy_id=s.id  where y.id=? ";	
		List<Map<String, Object>>   relist= iSQLQuery.query(sql,new Object[]{ids});		
		return relist;
	}*/
/*	@Override
	public List<Map<String, Object>> getYcjuofycsj(String idd) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}*/
	
	@Override
	public List<Map<String, Object>> ajaxRwslStatistics(PageConfig pc) {
		String inner_sql = "	SELECT sjy.id ,sjy.fd_mc,sjy.fd_sjkmc,sjy.fd_ip, sum(sl.fd_cljls) fd_cljls ,sum(sl.fd_ycjls) fd_ycjls FROM DSJZC_RWSL sl "+
					 "	JOIN DSJZC_RWB  rw ON sl.fd_rw_id=rw.id AND rw.fd_lx ="+Conts.FD_GZLX_CJ +  
					 "	JOIN DSJZC_GZPZ gz ON  rw.fd_gz_id=gz.id  "+
					 "	JOIN DSJZC_SJYB sjy on sjy.id = gz.fd_srsjy_id "+
					 "	GROUP BY sjy.id ,sjy.fd_mc,sjy.fd_sjkmc ,sjy.fd_ip  ORDER BY fd_cljls desc ";
		String sql = "SELECT sjy.id,sjy.fd_mc,sjy.fd_sjkmc,sjy.fd_ip,tempV.fd_cljls,tempV.fd_ycjls FROM DSJZC_SJYB sjy LEFT JOIN ("+inner_sql+") tempV ON sjy.id = tempV.id ";
		return this.iSQLQuery.query(sql, pc); 
	}
	
	@Override
	public List<Map<String, Object>> ajaxHomeRwslStatisticForEchart() {
		String inner_sql = "	SELECT sjy.id ,sjy.fd_mc,sjy.fd_sjkmc,sjy.fd_ip, sum(sl.fd_cljls) fd_cljls ,sum(sl.fd_ycjls) fd_ycjls FROM DSJZC_RWSL sl "+
				 "	JOIN DSJZC_RWB  rw ON sl.fd_rw_id=rw.id AND rw.fd_lx ="+Conts.FD_GZLX_CJ +  
				 "	JOIN DSJZC_GZPZ gz ON  rw.fd_gz_id=gz.id  "+
				 "	JOIN DSJZC_SJYB sjy on sjy.id = gz.fd_srsjy_id "+
				 "	GROUP BY sjy.id ,sjy.fd_mc,sjy.fd_sjkmc ,sjy.fd_ip  ORDER BY fd_cljls desc ";
		String sql = "SELECT sjy.id,sjy.fd_mc,sjy.fd_sjkmc,sjy.fd_ip,tempV.fd_cljls,tempV.fd_ycjls FROM DSJZC_SJYB sjy LEFT JOIN ("+inner_sql+") tempV ON sjy.id = tempV.id ";
		return this.iSQLQuery.query(sql);
	}
 
}