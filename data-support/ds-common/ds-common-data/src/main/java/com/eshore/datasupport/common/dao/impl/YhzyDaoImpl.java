package com.eshore.datasupport.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IYhzyDao;
import com.eshore.datasupport.common.pojo.Yhzy;

import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class YhzyDaoImpl extends JpaDaoImpl<Yhzy> implements IYhzyDao{

	@Override
	public List listxMenuById(String id) {
		List<Object>listParams = new ArrayList<Object>();
		String sql = " select z.fd_mc , z.id , z.fd_dz, z.fd_fjid, z.fd_tb,z.fd_xh  ,b.id yhid  "+
				" from dsjzc_zypz z       "+
				" left join dsjzc_yhzy y  "+
				" on z.id = y.fd_zyid  AND y.fd_yhid = ?  "+
			" left join dsjzc_yhb b   "+
			" on y.fd_yhid=b.id       ";
		if(StringUtils.isNotBlank(id)){
			listParams.add(id);
		}else{
			listParams.add("");
		}
		List<Map<String, Object>> list = this.iSQLQuery.query(sql,listParams.toArray());
		return list ;
	}
	
	@Override
	public List<Map<String, Object>> ajaxZtreeMenuListById(String id) {
		String sql = " select z.fd_mc , z.id , z.fd_dz, z.fd_fjid, z.fd_tb,z.fd_xh  ,b.id yhid  "+
				" from dsjzc_zypz z       "+
				" left join dsjzc_yhzy y  "+
				" on z.id = y.fd_zyid  AND y.fd_yhid = ?   "+
				" left join dsjzc_yhb b   "+
				" on y.fd_yhid=b.id    "
				+ "WHERE b.id is not null   ";
		List<Object>listParams = new ArrayList<Object>();
		listParams.add(id);
		List<Map<String, Object>> list = this.iSQLQuery.query(sql,listParams.toArray());
		return list ;
	}


	@Override
	public List findMenuListById(String id) {
		String sql = " SELECT fd_zyid FROM DSJZC_YHZY WHERE fd_yhid = ? ";
		List<Object>listParams = new ArrayList<Object>();
		listParams.add(id);
		List list = this.querySql(sql, listParams.toArray());
		return list ;
	}

	@Override
	public List<Yhzy> getSpecifyYhzy(String fd_yhid) {
		String sql  =  " FROM  Yhzy   ";
		List<Object>listParams = new ArrayList<Object>();
		if(StringUtils.isNotBlank(fd_yhid)){
			sql+="WHERE FD_YHID = ? " ;
			listParams.add(fd_yhid);
		}
		return this.query(sql,listParams.toArray());
	}

	@Override
	public List<Yhzy> getYhzyRecordByZyid(String zyid) {
		String sql  =  " FROM  Yhzy   ";
		List<Object>listParams = new ArrayList<Object>();
		if(StringUtils.isNotBlank(zyid)){
			sql+="WHERE FD_ZYID = ? " ;
			listParams.add(zyid);
		}
		return this.query(sql,listParams.toArray());
	}
}