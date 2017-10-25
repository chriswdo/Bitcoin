package  com.eshore.datasupport.metadata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.metadata.dao.IR_databaseDao;
import com.eshore.datasupport.metadata.pojo.R_database;
import com.eshore.datasupport.metadata.service.IR_databaseService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * kettle���Դ��ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class R_databaseServiceImpl extends BaseServiceImpl<R_database> implements IR_databaseService {

	@Autowired
	IR_databaseDao r_databaseDao;

	@Override
	public IBaseDao<R_database> getDao() {
		return (IBaseDao<R_database>)r_databaseDao;
	}
}
