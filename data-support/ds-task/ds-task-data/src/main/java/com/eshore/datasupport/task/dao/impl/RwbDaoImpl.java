package com.eshore.datasupport.task.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.util.Conts;
import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.task.dao.IRwbDao;
import com.eshore.datasupport.task.pojo.Rwb;
import com.eshore.datasupport.task.vo.TaskKettleVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class RwbDaoImpl extends JpaDaoImpl<Rwb> implements IRwbDao{
	@Value("#{configProperties['jdbc.driverClassName']}")
	private String db;
	private final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final Logger logger = Logger.getLogger(RwbDaoImpl.class);
	
	@Override
	public TaskKettleVo findRWJOB(Rwb rwb) {
//		List<Object> params = new ArrayList<Object>();
		
//		StringBuilder sql = new StringBuilder("SELECT a.id,");
//		sql.append("a.fd_rwbh,");
//		sql.append("a.fd_lx,");
//		sql.append("b.fd_gzmc,");
//		sql.append("b.r_job_id,");
//		sql.append("b.r_job_name,");
//		sql.append("b.r_job_desc,");
//		sql.append("a.fd_ddpl,");
//		sql.append(DataSourceUtil.getDateSqlString(db, "a.fd_scqdsj", "1")).append(" fd_scqdsj,");
//		sql.append("a.fd_rwzt,");
//		sql.append(DataSourceUtil.getDateSqlString(db, "a.fd_sjsjc", "1")).append(" fd_sjsjc,");
//		sql.append("a.fd_sjl,");
//		sql.append("a.fd_sjldw,");
//		sql.append("a.fd_zntz,");
//		sql.append("a.fd_qzrw_id,");
//		sql.append("a.fd_rwms,");
//		sql.append(" b.fd_srb_id,");
//		sql.append(" b.fd_srsjy_id");
//		sql.append(" FROM dsjzc_rwb a ");
//		sql.append(" LEFT JOIN dsjzc_gzpz b ON a.FD_GZ_ID=b.id ");	
//		
//		
//		if(StringUtils.isNotEmpty(dsJobId)){
//			sql.append("WHERE ");//
//			sql.append("  a.id = ? ");	
//			params.add(dsJobId);
//		} 
//		
//		logger.info(sql.toString());
//		List<TaskKettleVo> list= this.jdbcTemplate.query(sql.toString(),
//				params.toArray(),new TaskKettleMap());
//		if(list!=null && !list.isEmpty()){
//			return list.get(0);
//		}
		String sql = "SELECT b.fd_gzmc,b.r_job_id,b.r_job_name,b.r_job_desc,b.fd_srb_id,b.fd_srsjy_id FROM dsjzc_gzpz b WHERE b.id = ? ";
		List<Map<String,Object>> list = this.iSQLQuery.query(sql, new Object[]{rwb.getFd_gz_id()});
		TaskKettleVo vo = new TaskKettleVo();
		if(list!=null && !list.isEmpty()){
			Map<String,Object> map = list.get(0);
			vo.setId(rwb.getId());
			vo.setFd_rwbh(rwb.getFd_rwbh());
			vo.setFd_lx(((Byte)rwb.getFd_lx()).intValue());
			vo.setGzmc(map.get("FD_GZMC")+"");
			vo.setR_job_id(((BigDecimal)map.get("R_JOB_ID")).intValue());
			vo.setR_job_name(map.get("R_JOB_NAME")+"");
			vo.setR_job_desc(map.get("R_JOB_DESC")+"");
			vo.setFd_ddpl(rwb.getFd_ddpl());
			vo.setFd_rwzt(rwb.getFd_rwzt());
			vo.setFd_sjl(rwb.getFd_sjl());
			vo.setFd_sjldw(rwb.getFd_sjldw());
			vo.setFd_rwms(rwb.getFd_rwms());
			vo.setFd_zntz(rwb.getFd_zntz());
			vo.setFd_qzrw_id(rwb.getFd_qzrw_id());
			vo.setFd_srsjy_id(map.get("FD_SRSJY_ID")+"");
			vo.setFd_srb_id(map.get("FD_SRB_ID")+"");
			vo.setFd_scqdsj(rwb.getFd_scqdsj());
			vo.setFd_sjsjc(rwb.getFd_sjsjc());
		}else{
			throw new NullPointerException("任务没有找到对应的规则!");
		}
		return vo;
	}
	
	class TaskKettleMap implements RowMapper<TaskKettleVo> {
		@Override
		public TaskKettleVo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			TaskKettleVo vo = new TaskKettleVo();
			vo.setId(rs.getString("id"));
			vo.setFd_rwbh(rs.getString("fd_rwbh"));//任务编号
			vo.setFd_lx(rs.getInt("fd_lx"));//规则类型：0 采集，1 处理，2挖掘
			vo.setGzmc(rs.getString("fd_gzmc"));//规则名称
			vo.setR_job_id(rs.getInt("r_job_id"));//kettle 任务ID
			vo.setR_job_name(rs.getString("r_job_name"));//kettle 任务名称
			vo.setR_job_desc(rs.getString("r_job_desc"));//kettle 任务描述
			vo.setFd_ddpl(rs.getString("fd_ddpl"));//调度频率（Cron表达式）
			
			vo.setFd_rwzt(rs.getString("fd_rwzt"));//任务状态
			
			try {
				if(StringUtils.isNotBlank(rs.getString("fd_scqdsj"))){
					vo.setFd_scqdsj(ymdhms.parse(rs.getString("fd_scqdsj")));//首次启动时间
				}
				if(StringUtils.isNotBlank(rs.getString("fd_sjsjc"))){
					vo.setFd_sjsjc(ymdhms.parse(rs.getString("fd_sjsjc")));//数据时间戳
				}
			} catch (ParseException e) {
				 
				logger.info(e);
			}
			vo.setFd_sjl(rs.getInt("fd_sjl"));//单次任务执行处理的数据量
			vo.setFd_sjldw(rs.getString("fd_sjldw"));//数据量单位：分钟/小时/天/周/月/年，默认分钟
			vo.setFd_rwms(rs.getString("fd_rwms"));//任务描述
			vo.setFd_zntz(rs.getString("fd_zntz"));// 
			vo.setFd_qzrw_id(rs.getString("fd_qzrw_id"));//前置任务
			
			vo.setFd_srsjy_id(rs.getString("fd_srsjy_id"));//任务描述
			vo.setFd_srb_id(rs.getString("fd_srb_id"));//前置任务
			return vo;
		}
	}
	
	@Override
	 public void updateSJC(String startTime,String endTime,String id){
		if(StringUtils.isNotBlank(id)&& StringUtils.isNotBlank(endTime)){
			//加上fd_sjsjc=开始时间 是因为，对任务记录的操作有两个可能，一种是这里的时间戳更新，一种是任务的编辑， 当任务执行时间比较长时，且正好在任务执行时，用户编辑时间搓为A1，但任务执行完后也会更改时间，导致编辑失效 
			String sql="update dsjzc_rwb set FD_SJSJC= "+DataSourceUtil.getDateSqlString(db, "'"+endTime+"'", "0")+" where id='"+id+"' and fd_sjsjc= "+DataSourceUtil.getDateSqlString(db, "'"+startTime+"'", "0");
			logger.info(sql);
			super.jdbcTemplate.execute(sql);
		}
		
	 }

	@Override
	public List<Map<String, Object>> getCollectionTaskList(PageConfig pc,String ids,String qzrw_mc) {
		String sql = "SELECT "
				+ " r.id,r.fd_rwbh,r.FD_RWMS,r.FD_RWZT,g.fd_gzmc "
				+ " from dsjzc_rwb r ,dsjzc_gzpz g "
				+ " WHERE r.fd_lx=0  AND r.fd_gz_id = g.id  ";
				
		String sql_add = "";
		List<Object>params_sql = new ArrayList<Object>();
		if(StringUtils.isNotBlank(ids)){
			String []idsStr = ids.split("#");
			for(String strTemp : idsStr){
				sql_add +="?,";
				params_sql.add(strTemp);
			}
		}
		if(StringUtils.isNotBlank(sql_add)){
			sql = sql + "AND r.id not in (" +sql_add.substring(0, sql_add.length()-1)+")";
		}
		if(StringUtils.isNotBlank(qzrw_mc)){
			sql = sql + " AND g.fd_gzmc LIKE  ?  ";
			params_sql.add("%"+qzrw_mc+"%");
		}
		sql = sql +"ORDER BY r.fd_rwbh desc";
		return this.iSQLQuery.query(sql,params_sql.toArray(),pc);
	}

	@Override
	public List<Map<String, Object>> getSelectedCollectTaskList(String id) {
		StringBuffer sql = new StringBuffer("SELECT r.fd_qzrw_id  from dsjzc_rwb r  ");
		List<Object>params_sql = new ArrayList<Object>();
		if(StringUtils.isNotBlank(id)){
			sql.append(" WHERE r.id = ? ");
			params_sql.add(id);
		}
		List <Map<String,Object>>list = this.iSQLQuery.query(sql.toString(), params_sql.toArray());
		return list;
	}

	@Override
	public Map<String, Object> getQzrwInfo(String id_temp) {
		String sql = "SELECT  r.ID as \"id\" ,r.FD_RWBH as \"fd_rwbh\",r.FD_RWZT as \"fd_rwzt\",gz.FD_GZMC as \"fd_gzmc\"  FROM  DSJZC_RWB r JOIN DSJZC_GZPZ gz ON r.FD_GZ_ID = gz.ID  AND r.ID = ? "; 
		List <Map<String,Object>>list = this.iSQLQuery.query(sql.toString(), new Object[]{id_temp});
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	
}