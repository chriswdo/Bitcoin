package com.eshore.datasupport.task.vo;

import java.util.Date;

public class DataSupportJobInst {
	/** 目录名称：采集 ：acquisition   处理：cleaning      挖掘：      mining */
	String dirName;
	/**job 名称*/
	String name;
	/**数据开始时间*/
	String startTime;
	/**数据结束时间*/
	String endTime;
	/**id  */
	String id;
	/**更新数据*/
	Date updateTime;
	/**数据状态*/
	String dataState;
	
	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
