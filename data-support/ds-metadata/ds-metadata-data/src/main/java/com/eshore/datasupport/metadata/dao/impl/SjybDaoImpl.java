package com.eshore.datasupport.metadata.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.metadata.dao.ISjybDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class SjybDaoImpl extends JpaDaoImpl<Sjyb> implements ISjybDao{

	@Override
	public List<Sjyb> listSjybRecord(Map<String,String> hashMap, PageConfig pc) {
		String hql = "FROM Sjyb WHERE 1=1 ";
		List<Object>paramsSql = new ArrayList<Object>();
		if(StringUtils.isNotEmpty(hashMap.get("fd_mc"))){
			hql+=" AND FD_MC LIKE ?";
			paramsSql.add("%"+hashMap.get("fd_mc")+"%");
		}
		if(StringUtils.isNotEmpty(hashMap.get("fd_lx"))){
			hql+=" AND FD_LX = ?";
			paramsSql.add(hashMap.get("fd_lx"));
		}
		List<Sjyb> list = this.queryPage(hql,pc , paramsSql.toArray());
		this.entityManager.clear();
		return list;
	}
	
}