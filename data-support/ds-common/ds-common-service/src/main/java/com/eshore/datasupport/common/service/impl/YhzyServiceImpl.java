package  com.eshore.datasupport.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.eshore.datasupport.common.dao.IYhbDao;
import com.eshore.datasupport.common.dao.IYhzyDao;
import com.eshore.datasupport.common.dao.IZypzDao;
import  com.eshore.datasupport.common.pojo.Yhzy;
import com.eshore.datasupport.common.pojo.Zypz;
import  com.eshore.datasupport.common.service.IYhzyService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;


/**
 * �û���Դ��ϵҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class YhzyServiceImpl extends BaseServiceImpl<Yhzy> implements IYhzyService {

	@Autowired
	IYhzyDao yhzyDao;
	
	@Autowired
	IZypzDao zypzDao;

	@Override
	public IBaseDao<Yhzy> getDao() {
		return (IBaseDao<Yhzy>)yhzyDao;
	}

	@Override
	public List listxMenuById(String id,String basePath) {
		List<Map<String,Object>>retList =  yhzyDao.listxMenuById(id);
		sortListByFd_xh(retList);
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		for(int i=0;i<retList.size();i++ ){
			nodes.add(generateNode(retList,i,basePath));
		}
		return nodes;
	}
	
	private Map<String,Object> generateNode(List<Map<String,Object>> list,int flag,String basePath){
		Map<String,Object>  node = new HashMap<String,Object>();
		node.put("id", list.get(flag).get("ID"));
		node.put("pId", list.get(flag).get("FD_FJID"));
		node.put("name", list.get(flag).get("FD_MC"));
		node.put("icon", basePath+list.get(flag).get("FD_TB"));
		if(StringUtils.isNotBlank((String)list.get(flag).get("YHID"))){
			node.put("checked", true);
		}
		for(Map<String,Object>map : list){
			if(list.get(flag).get("ID").equals(map.get("FD_FJID"))){
				node.put("open", true);
			}
		}
		return node;
	}
	
	private void sortListByFd_xh(List<Map<String,Object>> list){
		Collections.sort(list, new Comparator<Map<String,Object>>(){
			@Override
			public int compare(Map<String,Object> o1, Map<String,Object> o2) {
				Integer a = o1.get("FD_XH")==null?0:(Integer.parseInt(o1.get("FD_XH")+"")) ;
				Integer b = o2.get("FD_XH")==null?0:(Integer.parseInt(o2.get("FD_XH")+"")) ;
				if(a - b!=0) {
					return a-b;
				}else{
					int a1 = o1.get("FD_MC")==null?0:(o1.get("FD_MC")+"").charAt(0) ;
					int b1 = o2.get("FD_MC")==null?0:(o2.get("FD_MC")+"").charAt(0) ;
					return a1-b1;
				}
			}
		});
	}

	@Override
	public List ajaxZtreeMenuListById(String id,String basePath) {
		List<Map<String,Object>>retList =  yhzyDao.ajaxZtreeMenuListById(id);
		sortListByFd_xh(retList);
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		for(int i=0;i<retList.size();i++ ){
			nodes.add(generateNode(retList,i,basePath));
		}
		return nodes;
	}
}
