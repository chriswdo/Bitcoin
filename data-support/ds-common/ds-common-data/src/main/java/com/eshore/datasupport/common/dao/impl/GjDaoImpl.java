package com.eshore.datasupport.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IGjDao;
import com.eshore.datasupport.common.pojo.Gj;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class GjDaoImpl extends JpaDaoImpl<Gj> implements IGjDao{

	@Override
	public List listxAlarmRecord(Map<String, String> params, PageConfig pc) {
		String sql = " select g.id, gz.fd_gzmc,g.fd_gjfs,y.id fd_yh_id ,y.fd_yhmc,y.fd_yx,y.fd_lxfs,g.fd_zt ,r.id fd_rw_id from dsjzc_gj g, dsjzc_yhb y,dsjzc_rwb r ,dsjzc_gzpz gz  where g.fd_rwid=r.id AND g.fd_yh_id=y.id AND r.fd_gz_id=gz.id  ";
		List<Object> paramsSql = new ArrayList<Object>();
		if(StringUtils.isNotBlank(params.get("fd_gzmc"))){
			sql+=" AND  gz.fd_gzmc LIKE  ?     " ;
			paramsSql.add("%"+params.get("fd_gzmc")+"%");
		}
		if(StringUtils.isNotBlank(params.get("fd_yhmc"))){
			sql+=" AND  y.fd_yhmc LIKE  ?     " ;
			paramsSql.add("%"+params.get("fd_yhmc")+"%");
		}
		if(StringUtils.isNotBlank(params.get("fd_gjfs"))){
			sql+=" AND  g.fd_gjfs =  ?     " ;
			paramsSql.add(params.get("fd_gjfs"));
		}
		sql+="  ORDER BY gz.fd_gzmc   " ;
		return this.iSQLQuery.query(sql, paramsSql.toArray(), pc);
	}

	@Override
	public List getGjRecordByRwid(String rwid) {
		String hql = "SELECT g.fd_zt,g.fd_gjfs,y.fd_lxfs,y.fd_yx from DSJZC_GJ g ,DSJZC_YHB y  WHERE g.fd_yh_id=y.id AND   g.fd_rwid = ?  and g.fd_zt='Y'";
		return this.iSQLQuery.query(hql, new Object[]{rwid});
	}

	@Override
	public boolean existGjByRwid(String rwid) {
		String hql = "FROM Gj WHERE fd_rwid = ? ";
		List<Gj> list = this.query(hql, new Object[]{rwid});
		if(list == null || list.isEmpty()){
			return false;
		}
		return true;
	}
	
}