package com.eshore.datasupport.task.vo;

import java.util.Date;

/**
 * 业务系统任务类
 * 
 * @author zhl
 * 
 */
public class DataSupportJob {
	/** 目录名称：采集 ：acquisition 处理：cleaning 挖掘： mining */
	String directory;
	/** kettejob 名称 */
	String name;
	/***/
	String kettleJob;
	String id;
	Date updateTime;
	String dataState;

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKettleJob() {
		return kettleJob;
	}

	public void setKettleJob(String kettleJob) {
		this.kettleJob = kettleJob;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		if (updateTime == null) {
			return null;
		}
		return (Date) updateTime.clone();
	}

	public void setUpdateTime(Date updateTime) {
		if (updateTime == null) {
			this.updateTime = null;
		} else {
			this.updateTime = (Date) updateTime.clone();
		}
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

}
