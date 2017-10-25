package com.eshore.datasupport.task.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.task.dao.IYcjlDao;
import com.eshore.datasupport.task.pojo.Ycjl;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class YcjlDaoImpl extends JpaDaoImpl<Ycjl> implements IYcjlDao{

	@Override
	public List<Map<String, Object>> exportYcdata(String idd) {
		String sql="select t.id,t.fd_yxcs,y.fd_srsjy_id,y.fd_ycsj  from dsjzc_rwsl t ,dsjzc_ycjl y where t.id=y.id   and t.id=?  ";	
		List<Map<String, Object>>   relist= iSQLQuery.query(sql,new Object[]{idd});		
		return relist;
	}

}