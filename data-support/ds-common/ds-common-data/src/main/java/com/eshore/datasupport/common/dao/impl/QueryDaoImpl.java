package com.eshore.datasupport.common.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IQueryDao;
import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.common.vo.JkVo;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class QueryDaoImpl extends JpaDaoImpl<Jkdy> implements IQueryDao{
	
	private static final Logger logger = Logger.getLogger(QueryDaoImpl.class);
			
	@Override
	public JkVo getJkdyByJkcode(String jkCode) {
		String sql = "SELECT * FROM DSJZC_JKDY jk, DSJZC_SJYB sjy WHERE jk.JK_CODE = ? AND jk.sjy_id = sjy.id";
		try{
			JkVo jkvo = this.jdbcTemplate.queryForObject(sql, new Object[]{jkCode},new RowMapper<JkVo>(){
				@Override
				public JkVo mapRow(ResultSet arg0, int arg1) throws SQLException {
					JkVo jkvo = new JkVo();
					jkvo.setFd_gxr(arg0.getString("FD_GXR"));
					jkvo.setFd_gxsj(arg0.getDate("FD_GXSJ"));
					jkvo.setId(arg0.getString("ID"));
					jkvo.setJk_code(arg0.getString("JK_CODE"));
					jkvo.setJk_name(arg0.getString("JK_NAME"));
					jkvo.setJk_zt(arg0.getString("JK_ZT"));
					jkvo.setKeywords(arg0.getString("KEYWORDS"));
					jkvo.setPage_pre(arg0.getString("PAGE_PRE"));
					jkvo.setPage_suf(arg0.getString("PAGE_SUF"));
					jkvo.setSjy_id(arg0.getString("SJY_ID"));
					jkvo.setSql_info(arg0.getString("SQL_INFO"));
					jkvo.setFd_cjr(arg0.getString("FD_CJR"));
					jkvo.setFd_cjsj(arg0.getDate("FD_CJSJ"));
					jkvo.setFd_dk(arg0.getInt("FD_DK"));
					jkvo.setFd_ip(arg0.getString("FD_IP"));
					jkvo.setFd_kid(arg0.getLong("FD_KID"));
					jkvo.setFd_lx(arg0.getString("FD_LX"));
					jkvo.setFd_mc(arg0.getString("FD_MC"));
					jkvo.setFd_mm(arg0.getString("FD_MM"));
					jkvo.setFd_ms(arg0.getString("FD_MS"));
					jkvo.setFd_sjklx(arg0.getString("FD_SJKLX"));
					jkvo.setFd_sjkmc(arg0.getString("FD_SJKMC"));
					jkvo.setFd_yhm(arg0.getString("FD_YHM"));
					jkvo.setId(arg0.getString("ID"));
					return jkvo;
				}});
			return jkvo;
		}catch(Exception e){
			logger.info(e);
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> executeSelectSql(JkVo jkvo) throws Exception {
		String url = DataSourceUtil.getUrl(jkvo.getFd_sjklx(), jkvo.getFd_ip(), jkvo.getFd_dk(), jkvo.getFd_sjkmc());
		String driver = DataSourceUtil.getDriverName(jkvo.getFd_sjklx());
		String sqlInfo = jkvo.getPage_pre()+jkvo.getSql_info()+jkvo.getPage_suf();
		List<Map<String, Object>> list = DataSourceUtil.getQuerySql(url,driver,jkvo.getFd_yhm(),jkvo.getFd_mm(), sqlInfo);
		return list;
	}

	@Override
	public void executeSql(JkVo jkvo) {
		String sql = jkvo.getSql_info();
		this.jdbcTemplate.execute(sql);
	}

}
