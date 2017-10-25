package  com.eshore.datasupport.metadata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.metadata.dao.IXxzdDao;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.service.IXxzdService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * Ԫ��ݱ�ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class XxzdServiceImpl extends BaseServiceImpl<Xxzd> implements IXxzdService {
		 
	@Autowired
	IXxzdDao xxzdDao;
	
	 
	@Override
	public IBaseDao<Xxzd> getDao() {
		return (IBaseDao<Xxzd>)xxzdDao;
	}


	
}
