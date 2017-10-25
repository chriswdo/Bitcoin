package  com.eshore.datasupport.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.eshore.datasupport.common.service.IGjService;
import com.eshore.datasupport.task.dao.IGzpzDao;
import  com.eshore.datasupport.common.pojo.Gj;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

import com.eshore.datasupport.common.dao.IGjDao;

/**
 * 告警service
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class GjServiceImpl extends BaseServiceImpl<Gj> implements IGjService {

	private final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	IGjDao gjDao;
	
	@Autowired
	IGzpzDao gzpzDao;

	@Override
	public IBaseDao<Gj> getDao() {
		return (IBaseDao<Gj>)gjDao;
	}

	@Override
	public List<Gj> listxAlarmRecord(Map<String, String> params, PageConfig pc) {
		return gjDao.listxAlarmRecord(params,pc);
	}

	@Override
	public List<Map<String, Object>> ajaxTaskInfo(Map<String, Object> params, PageConfig pc) {
		List<Map<String,Object>>list = this.gzpzDao.findRwbListForGj(params, pc);
		//转换时间参数
		translateGxsj(list);
		return list;
	}
	
	private void translateGxsj(List<Map<String,Object>>list){
		for(Map<String,Object> m : list){
			m.put("FD_GXSJ",m.get("FD_GXSJ")==null?"":ymdhms.format((Date)m.get("FD_GXSJ")));
		}
	}
}
