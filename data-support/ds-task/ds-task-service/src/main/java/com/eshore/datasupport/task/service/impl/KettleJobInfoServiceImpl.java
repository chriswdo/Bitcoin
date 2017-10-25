package com.eshore.datasupport.task.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.task.dao.IKettleJobInfoDao;
import com.eshore.datasupport.task.service.IKettleJobInfoService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

@Service
@Transactional
public class KettleJobInfoServiceImpl extends BaseServiceImpl implements IKettleJobInfoService{

	@Autowired
	IKettleJobInfoDao KettleJobInfoDao;
	
	
	@Override
	public IBaseDao getDao() {
		return KettleJobInfoDao;
	}
	


	public List<String> getInputInfo(String jobId){
		List<String>listStr = KettleJobInfoDao.getAllTransId(jobId);
		List<String>nameList = new ArrayList<String>();
		for(String transId:listStr){
			List<String> listTemp = getInputByTransId(transId);
			nameList.addAll(listTemp);
		}
		return nameList;
	}
	
	private List<String> getInputByTransId(String transId){
		List<String>nameLists = KettleJobInfoDao.getInputName(transId);
		return nameLists;
	}
	
	
	public List<String> getOutputInfo(String jobId){
		List<String>listStr = KettleJobInfoDao.getAllTransId(jobId);
		List<String>nameList = new ArrayList<String>();
		for(String transId:listStr){
			List<String> listTemp = getOutputByTransId(transId);
			nameList.addAll(listTemp);
		}
		return nameList;
	}
	
	private List<String> getOutputByTransId(String transId){
		List<String>nameLists = KettleJobInfoDao.getOutputName(transId);
		return nameLists;
	}
	
	public List<String> getValidInfo(String jobId){
		List<String>listStr = KettleJobInfoDao.getAllTransId(jobId);
		List<String>nameList = new ArrayList<String>();
		for(String transId:listStr){
			List<String> listTemp = getValidByTransId(transId);
			nameList.addAll(listTemp);
		}
		return nameList;
	}

	private List<String> getValidByTransId(String transId) {
		List<String>listStr = KettleJobInfoDao.getValidByTransId(transId);
		return listStr;
	}

	@Override
	public List<Map<String,Object>> getChartInfo(String jobId) {
		List<Map<String,Object>>list = KettleJobInfoDao.getChartInfo(jobId);
		return list;
	}
	
	public Set<String> getKettleJobId(String inputDataSourceId,String outputDataSourceId){
		Set<String>retSet = new HashSet<String>();
		Set<String> listInput = new HashSet<String>();
		if(StringUtils.isNotBlank(inputDataSourceId)){
			listInput = KettleJobInfoDao.getKettleJobIdByinputSource(inputDataSourceId);
		}
		Set<String>listOutput = new HashSet<String>();
		if(StringUtils.isNotBlank(outputDataSourceId)){
			listOutput = KettleJobInfoDao.getKettleJobIdByoutputSource(outputDataSourceId);
		}
		for(String str : listInput ){
			if(listOutput.contains(str)){
				retSet.add(str);
			}
		}
		return retSet;
	}
}
