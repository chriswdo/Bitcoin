package  com.eshore.datasupport.task.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.task.dao.IYcjlDao;
import com.eshore.datasupport.task.pojo.Ycjl;
import com.eshore.datasupport.task.service.IYcjlService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * 
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class YcjlServiceImpl extends BaseServiceImpl<Ycjl> implements IYcjlService {

	@Autowired
	IYcjlDao ycjlDao;

	@Override
	public IBaseDao<Ycjl> getDao() {
		return (IBaseDao<Ycjl>)ycjlDao;
	}

	@Override
	public List<Map<String, Object>> exportYcdata(String idd) {
		List<Map<String, Object>>  res=ycjlDao.exportYcdata(idd);
		return res;
	}
}
