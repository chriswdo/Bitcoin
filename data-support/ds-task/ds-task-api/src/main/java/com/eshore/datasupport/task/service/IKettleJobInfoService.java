package com.eshore.datasupport.task.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eshore.khala.core.api.IBaseService;

public interface IKettleJobInfoService extends IBaseService{

	/**
	 * 获取输入表数据
	 * @param jobId
	 * @return
	 */
	public List<String> getInputInfo(String jobId);
	
	/**
	 * 获取输出表数据
	 * @param jobId
	 * @return
	 */
	public List<String> getOutputInfo(String jobId);
	
	/**
	 * 获取校验信息
	 * @param jobId
	 * @return
	 */
	public List<String> getValidInfo(String jobId);

	/**
	 * 返回图像的信息
	 * @param jobId
	 * @return
	 */
	public List<Map<String,Object>> getChartInfo(String jobId);
	
	/**
	 * 返回找到的jobid
	 * @param inputDataSourceId  输入源id
	 * @param outputDataSourceId	输出源id
	 * @return
	 */
	public Set<String> getKettleJobId(String inputDataSourceId,String outputDataSourceId);
}
