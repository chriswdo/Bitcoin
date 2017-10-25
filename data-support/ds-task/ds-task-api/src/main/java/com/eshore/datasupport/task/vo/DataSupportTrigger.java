package com.eshore.datasupport.task.vo;

import java.util.Date;

/**
 * 业务系统任务调度类
 * @author zhl
 *
 */
public class DataSupportTrigger {

	private DataSupportJob job;//关联job
	private String cronExp;
	private Date dataStartTime;
	private Integer dataTimeSpan;
	private Integer timeSpanUnit;

	private String id;
	private Date updateTime;
	private String dataState;

	public DataSupportJob getJob() {
		return job;
	}

	public void setJob(DataSupportJob job) {
		this.job = job;
	}

	public String getCronExp() {
		return cronExp;
	}

	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

	public Date getDataStartTime() {
		if (dataStartTime == null) {
			return null;
		}
		return (Date) dataStartTime.clone();
	}

	public void setDataStartTime(Date dataStartTime) {
		if (dataStartTime == null) {
			this.dataStartTime = null;
		} else {
			this.dataStartTime = (Date) dataStartTime.clone();
		}
	}

	public Integer getDataTimeSpan() {
		return dataTimeSpan;
	}

	public void setDataTimeSpan(Integer dataTimeSpan) {
		this.dataTimeSpan = dataTimeSpan;
	}

	public Integer getTimeSpanUnit() {
		return timeSpanUnit;
	}

	public void setTimeSpanUnit(Integer timeSpanUnit) {
		this.timeSpanUnit = timeSpanUnit;
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
