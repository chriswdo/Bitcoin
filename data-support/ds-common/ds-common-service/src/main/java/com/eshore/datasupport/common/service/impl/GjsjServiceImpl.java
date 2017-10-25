package  com.eshore.datasupport.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.eshore.datasupport.common.service.IGjsjService;
import  com.eshore.datasupport.common.pojo.Gjsj;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

import com.eshore.datasupport.common.dao.IGjsjDao;

/**
 * �澯���ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class GjsjServiceImpl extends BaseServiceImpl<Gjsj> implements IGjsjService {

	@Autowired
	IGjsjDao gjsjDao;

	@Override
	public IBaseDao<Gjsj> getDao() {
		return (IBaseDao<Gjsj>)gjsjDao;
	}
	
	@Override
	public List<Map<String, String>> ajaxHomeGjInfo(PageConfig pc) {
		return gjsjDao.ajaxHomeGjInfo(pc);
	}
	
}
