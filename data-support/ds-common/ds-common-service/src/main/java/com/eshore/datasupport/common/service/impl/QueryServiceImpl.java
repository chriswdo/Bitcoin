package com.eshore.datasupport.common.service.impl;

import java.util.List;
import java.util.Map;

import org.pentaho.di.core.encryption.KettleTwoWayPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.dao.IQueryDao;
import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.service.IQueryService;
import com.eshore.datasupport.common.vo.JkVo;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

@Service
@Transactional
public class QueryServiceImpl extends BaseServiceImpl<Jkdy> implements IQueryService{

	@Autowired
	IQueryDao jkdyDao;
	
	@Override
	public IBaseDao<Jkdy> getDao() {
		return null;
	}

	@Override
	public JkVo getJkdyByJkcode(String jkCode) {
		JkVo jkvo = jkdyDao.getJkdyByJkcode(jkCode) ;
		return jkvo;
	}

	@Override
	public List<Map<String, Object>> executeSelectSql(JkVo jkvo) throws Exception {
		//解密密码
		KettleTwoWayPasswordEncoder kp = new KettleTwoWayPasswordEncoder();
		jkvo.setFd_mm(kp.decode(jkvo.getFd_mm()));
		//执行sql
		return jkdyDao.executeSelectSql(jkvo);
	}

	@Override
	public boolean checkSqlContainSELECT(JkVo jkvo) {
		if(jkvo.getSql_info().toUpperCase().indexOf("SELECT")!=-1){
			return true;
		}
		return false;
	}

	@Override
	public void executeSql(JkVo jkvo) {
		jkdyDao.executeSql(jkvo);
	}

	
}
