package com.eshore.datasupport.task.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;

import java.util.List;
import java.util.Map;

import com.eshore.datasupport.task.pojo.Rwb;
import com.eshore.datasupport.task.vo.TaskKettleVo;

/**
 * 
 */
public interface IRwbService extends IBaseService<Rwb> {
	/**
	 * 添加job到quarzs
	 * @param jobId
	 */
    public void addJobScheduer(Rwb rwb);
    
    /**
     * 从quarzs移除job
     * @param jobId
     */
    public void removeJobScheduer(Rwb rwb);
    
    /**
     * 查询
     * @param dsJobId
     * @return
     */
    public TaskKettleVo findRWJOB(String dsJobId);
    
    public void updateSJC(String startTime,String endTime,String id);
    
    /**
     * 改变任务状态
     * @param id
     * @param status
     * @return
     */
    public String changeTaskStatus(String id ,String status);
    
    /**
     * 更新任务且改变状态
     * @param rwb
     */
	public void updateAndChangeStatus(Rwb rwb);
	
	/**
	 * 添加任务且改变状态
	 * @param rwb
	 */
	public void saveAndChangeStatus(Rwb rwb);
	
	public List<Map<String, Object>> getCollectionTaskList(PageConfig pc, String ids, String qzrw_mc);

	public List<Map<String, Object>> getSelectedCollectTaskList(String id);

}
