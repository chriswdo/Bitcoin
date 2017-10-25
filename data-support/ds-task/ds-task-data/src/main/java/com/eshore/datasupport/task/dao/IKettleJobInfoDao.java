package com.eshore.datasupport.task.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eshore.khala.core.data.api.dao.IBaseDao;

public interface IKettleJobInfoDao extends IBaseDao{

	List<String> getAllTransId(String jobId);

	List<String> getInputName(String transId);

	List<String> getOutputName(String transId);

	List<String> getValidByTransId(String transId);

	List<Map<String, Object>> getChartInfo(String jobId);

	Set<String> getKettleJobIdByinputSource(String inputDataSourceId);

	Set<String> getKettleJobIdByoutputSource(String outputDataSourceId);

}
