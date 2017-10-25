package  com.eshore.datasupport.common.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.eshore.datasupport.common.dao.IYhbDao;
import com.eshore.datasupport.common.dao.IYhzyDao;
import com.eshore.datasupport.common.dao.IZypzDao;
import com.eshore.datasupport.common.pojo.Yhzy;
import  com.eshore.datasupport.common.pojo.Zypz;
import  com.eshore.datasupport.common.service.IZypzService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * �����֧��ƽ̨�û���ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class ZypzServiceImpl extends BaseServiceImpl<Zypz> implements IZypzService {
	private static final Logger logger = Logger.getLogger(ZypzServiceImpl.class);
	
	@Autowired
	IZypzDao zypzDao;
	
	@Autowired
	IYhzyDao yhzyDao;
	
	@Autowired
	IYhbDao yhbDao;

	@Override
	public IBaseDao<Zypz> getDao() {
		return (IBaseDao<Zypz>)zypzDao;
	}

	@Override
	public List<Zypz> getParentMenu() {
		PageConfig pc = new  PageConfig();
		pc.setPageSize(10000);
		List<Zypz>list = this.list(new HashMap(),pc);
		List<Zypz>retList = new ArrayList<Zypz>();
		for(Zypz z:list){
			if("0".equals(z.getFd_fjid())){
				retList.add(z);
			}
		}
		sortListByFd_xh(retList);
		return retList;
	}

	@Override
	public Map<String, String> getIconInfo() {
		Properties pro = new Properties();
		try {
			pro.load(ZypzServiceImpl.class.getClassLoader().getResourceAsStream("iconInfo.properties"));
		} catch (FileNotFoundException e) {
			logger.info(e);
		} catch (IOException e1) {
			logger.info(e1);
		}
		Map<String, String> map = new HashMap<String,String>();
		Enumeration  keys =  pro.keys();
		while(keys.hasMoreElements()){
			String key = (String) keys.nextElement();
			map.put(key, pro.getProperty(key));
		}
		return map;
	}

	@Override
	public List<Zypz> ajaxMenuListById(String id) {
		PageConfig pc = new PageConfig();
		pc.setPageSize(10000);
		List<Zypz>menuAll = this.list(new HashMap(), pc);
		List menuId = yhzyDao.findMenuListById(id);
		List<Zypz>menuRet = new ArrayList<Zypz>();
		for(Object objs : menuId){
			String objid = ""+objs;
			for(Zypz z : menuAll){
				if(z.getId().equals(objid)){
					menuRet.add(z);
					break;
				}
			}
		}
		return menuRet;
	}

	@Override
	public List<Map<String, Object>> getSortedList(List<Zypz> list) {
		//对菜单进行排序
		sortListByFd_xh(list);
		//对父子结构进行排序
		List<Map<String,Object>>retList = generateTreegridFormatData(list);
		return retList;
	}
	
	private List<Map<String,Object>> generateTreegridFormatData(List<Zypz> list){
		List<Map<String,Object>>retList = new ArrayList<Map<String,Object>>();
		Integer i=1;
		for(Zypz z: list){
			if(!"0".equals(z.getFd_fjid())){
				continue;
			}
			Integer level = 0;
			i = getZypzSortParams(z,i,list,retList,level);
		}
		return retList;
	}
	
	private void sortListByFd_xh(List<Zypz> list){
		Collections.sort(list, new Comparator<Zypz>(){
			@Override
			public int compare(Zypz o1, Zypz o2) {
				int re = (o1.getFd_xh()==null?0:o1.getFd_xh())-(o2.getFd_xh()==null?0:o2.getFd_xh());
				if(re!=0){
					
					return re;
				}else{
					int a1 = o1.getFd_mc()==null?0:o1.getFd_mc().charAt(0);
					int a2 = o2.getFd_mc()==null?0:o2.getFd_mc().charAt(0);
					return a1-a2;
				}
			}
		});
	}
	
	private int getZypzSortParams(Zypz zypz , Integer i ,List<Zypz>list,List<Map<String,Object>>retList,Integer level){
		Integer field_level = level;
		Integer field_i = i;
		Map<String,Object>temp = new HashMap<String,Object>();
		temp.put("id", zypz.getId());
		temp.put("fd_mc", zypz.getFd_mc());
		temp.put("fd_fjid", zypz.getFd_fjid());
		temp.put("fd_tb", zypz.getFd_tb());
		temp.put("fd_dz", zypz.getFd_dz());
		temp.put("fd_xh", zypz.getFd_xh());
		temp.put("lft", field_i++);
		temp.put("level", field_level++);
		retList.add(temp);
		for(Zypz z: list){
			if(zypz.getId().equals(z.getFd_fjid())){
				field_i = getZypzSortParams(z,field_i,list,retList,field_level);
			}
		}
		temp.put("rgt", field_i++);
		return field_i;
	}
	
	
	@Override
	public Map<String, String> deleteArrayZypz(String idArrStr) {
		Map<String,String>retMap = new HashMap<String,String>();
		JSONArray jarray = JSONArray.parseArray(idArrStr);
		for(Object o : jarray){
			 String id = (String)o;
			 List<Yhzy> list = yhzyDao.getYhzyRecordByZyid(id);
			 if(list!=null && !list.isEmpty()){
				 Zypz zy = zypzDao.get(id);
				 String retInfo= "菜单\""+zy.getFd_mc()+"\"";
				 retInfo+="已被使用 ,不能删除!";
				 retMap.put("result", "fail");
				 retMap.put("retInfo", retInfo);
				 return retMap;
			 }
		}
		for(Object o : jarray){
			 String id = (String)o;
			 deletexZypzRecord(id);
		}
		retMap.put("result", "success");
		return retMap;
	}
	
	@Override
	public void deletexZypzRecord(String id) {
		//删除子菜单
		deleteChildrenZypz(id);
	}
	
	private void deleteChildrenZypz(String fd_fjid){
		List<Zypz> list = zypzDao.getChildrenZypz(fd_fjid);
		for(Zypz z : list){
			deleteChildrenZypz(z.getId());
		}
		zypzDao.delete(fd_fjid);
	}
}
