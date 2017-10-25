package com.eshore.datasupport.common.service;

public interface IAlarmNotifyService {

	/**
	 * 
	 * @param rwid	任务id
	 * @param content 内容
	 */
	public boolean sendYcInfo(String rwid,String content);
}
