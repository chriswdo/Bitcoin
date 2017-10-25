package com.eshore.datasupport.metadata.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.metadata.dao.IR_databaseDao;
import com.eshore.datasupport.metadata.pojo.R_database;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class R_databaseDaoImpl extends JpaDaoImpl<R_database> implements IR_databaseDao{

	@Override
	public void saveDatabase(R_database database) {
		database.setId_database(getIdForInsert());
		this.save(database);
	}
	
	private  Long getIdForInsert() {
		List<Map<String ,Object>> list = this.iSQLQuery.query("SELECT MAX(ID_DATABASE) id FROM  R_DATABASE");
		if(list.get(0).get("id")==null){
			return 1l;
		}
		return Long.parseLong(list.get(0).get("id")+"")+1l;
	}

	@Override
	public void setDatabase_type(R_database database,String sjklx) {
		String sql =  "SELECT ID_DATABASE_TYPE ,CODE FROM R_DATABASE_TYPE ";
		List <Map<String,Object>> list = this.iSQLQuery.query(sql);
		for(Map<String,Object> m : list){
			if(sjklx.equalsIgnoreCase((String) m.get("CODE"))){
				database.setId_database_type(((BigDecimal) m.get("ID_DATABASE_TYPE")).intValue());
			}
		}
		
	}

}