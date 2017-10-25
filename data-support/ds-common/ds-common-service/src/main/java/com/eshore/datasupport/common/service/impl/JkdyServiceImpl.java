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
import com.eshore.datasupport.common.service.IJkdyService;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.JkdyVo;
import com.eshore.datasupport.task.dao.IGzpzDao;
import  com.eshore.datasupport.common.pojo.Gj;
import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

import com.eshore.datasupport.common.dao.IGjDao;
import com.eshore.datasupport.common.dao.IJkdyDao;

/**
 * 告警service
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class JkdyServiceImpl extends BaseServiceImpl<Jkdy> implements IJkdyService {

	private final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	IJkdyDao jkdyDao;
	
	@Autowired
	IGzpzDao gzpzDao;

	@Override
	public IBaseDao<Jkdy> getDao() {
		return (IBaseDao<Jkdy>)jkdyDao;
	}

	@Override
	public List<JkdyVo> getInterfaceMeg(String jkname, PageConfig pc) {
		return jkdyDao.getInterfaceMeg(jkname,pc);
	}

	@Override
	public String changeInterdaceStatus(String id, String status) {
		Jkdy jk=this.get(id);
		jk.setJk_zt(status);
		this.update(jk);
		return "success";
	}

	@Override
	public String savexJkMessage(Jkdy jk,String userid) {
		List<Jkdy>  li= this.getDataBynamecode(jk.getJk_code()) 	;
		if(li.isEmpty()){
	          jk.setFd_gxr(userid);
	          jk.setFd_gxsj(new Date());
	          jk.setKeywords(jk.getKeywords().substring(1));
			  this.save(jk);
			  return "success";
		}else{
			  return "repeat";	
		}

		  
	}

	@Override
	public String updateJkMessage(Jkdy jk,String userid) {
/*		jk.setFd_gxr(userid);
		jk.setFd_gxsj(new Date());*/
		Jkdy inter=this.get(jk.getId());
		inter.setFd_gxr(userid);
		inter.setFd_gxsj(new Date());
		inter.setFd_jkbz(jk.getFd_jkbz());
		inter.setJk_code(jk.getJk_code());
		inter.setJk_name(jk.getJk_name());
		inter.setKeywords(jk.getKeywords().substring(1));
		inter.setSjy_id(jk.getSjy_id());
		inter.setSql_info(jk.getSql_info());
		this.update(inter);
		return "success";
	}

	@Override
	public List<Jkdy> getDataBynamecode(String code) {
		List<Jkdy> re=jkdyDao.getDataBynamecode(code);
		return re;
	}
}
