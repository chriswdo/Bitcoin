package com.eshore.datasupport.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IYhbDao;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.pojo.Zypz;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class YhbDaoImpl extends JpaDaoImpl<Yhb> implements IYhbDao{
	
	@Override
	public List<Yhb> listByYhmc(Map<String,String> params,PageConfig pc) {
		String sql = " FROM Yhb WHERE fd_dlmc !='admin' ";
		List<Object> paramsSql = new ArrayList<Object>();;
		if(StringUtils.isNotBlank(params.get("fd_yhmc"))){
			sql+=" AND  fd_yhmc LIKE  ?     " ;
			paramsSql.add("%"+params.get("fd_yhmc")+"%");
		}
		if(StringUtils.isNotBlank(params.get("fd_zt"))){
			sql+=" AND  fd_zt =  ?     " ;
			paramsSql.add(params.get("fd_zt"));
		}
		sql+=" ORDER BY fd_yhmc     " ;
		return this.queryPage(sql, pc, paramsSql.toArray());
	}

	@Override
	public Yhb getYhbByFd_dlmc(String fd_dlmc) {
		String sql = "FROM Yhb WHERE fd_dlmc=? ";
		List<Yhb> list = this.query(sql, new Object[]{fd_dlmc});
		return (list!=null && !list.isEmpty())?list.get(0):null;
	}

}