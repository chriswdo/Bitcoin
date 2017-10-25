package  com.eshore.datasupport.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.metadata.dao.IR_database_attributeDao;
import com.eshore.datasupport.metadata.pojo.R_database_attribute;
import com.eshore.datasupport.metadata.service.IR_database_attributeService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * kettle���Դ���Ա�ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class R_database_attributeServiceImpl extends BaseServiceImpl<R_database_attribute> implements IR_database_attributeService {

	@Autowired
	IR_database_attributeDao r_database_attributeDao;

	@Override
	public IBaseDao<R_database_attribute> getDao() {
		return (IBaseDao<R_database_attribute>)r_database_attributeDao;
	}

}
