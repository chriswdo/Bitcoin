package com.eshore.datasupport.metadata.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.metadata.dao.IR_database_attributeDao;
import com.eshore.datasupport.metadata.pojo.R_database_attribute;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class R_database_attributeDaoImpl extends JpaDaoImpl<R_database_attribute> implements IR_database_attributeDao{

	
	@Override
	public void saveAttributeList(List<R_database_attribute> list) {
		Long id = getIdForInsert();
		int i=0;
		for(R_database_attribute r : list){
			r.setId_database_attribute(id+(i++));
			this.save(r);
		}
		
	}
	
	private  Long getIdForInsert() {										 
		List<Map<String,Object>> list = this.iSQLQuery.query("SELECT MAX(ID_DATABASE_ATTRIBUTE) id FROM R_DATABASE_ATTRIBUTE ");
		if(list.get(0).get("id")==null){
			return 1l;
		}
		return Long.parseLong(list.get(0).get("id")+"")+1l;
	}

	@Override
	public void deleteByR_databaseId(Long id_database) {
		this.jdbcTemplate.execute("delete from R_DATABASE_ATTRIBUTE WHERE ID_DATABASE = " +id_database);
	}

	@Override
	public void updatePort(Long id_database, Integer fd_dk) {
		String sql = "update r_database_attribute set value_str = '"+fd_dk+"' where id_database="+id_database+" and code = 'PORT_NUMBER'";
		this.jdbcTemplate.execute(sql);
	}
}