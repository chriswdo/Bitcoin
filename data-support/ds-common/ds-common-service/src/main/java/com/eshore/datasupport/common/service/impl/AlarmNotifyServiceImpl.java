package com.eshore.datasupport.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.dao.IGjDao;
import com.eshore.datasupport.common.dao.IGjsjDao;
import com.eshore.datasupport.common.pojo.Gjsj;
import com.eshore.datasupport.common.service.IAlarmNotifyService;
import com.eshore.datasupport.common.util.Conts;

@Service
@Transactional
public class AlarmNotifyServiceImpl implements  IAlarmNotifyService{

	@Autowired
	IGjDao gjDao;
	@Autowired
	IGjsjDao gjsjDao;
	
	private static final Logger logger = Logger.getLogger(AlarmNotifyServiceImpl.class);

	@Override
	public boolean sendYcInfo(String rwid, String content) {
		Gjsj gjsj = new Gjsj();
		gjsj.setFd_gjsj(new Date());
		//参数检查
		if(StringUtils.isBlank(rwid) ){
			logger.info("告警失败！任务id不能为空");
			gjsj.setFd_gjbz("1");
			gjsjDao.save(gjsj);
			return false;
		}
		if(StringUtils.isBlank(content)){
			logger.info("告警失败！告警内容不能为空");
			gjsj.setFd_gjbz("1");
			gjsjDao.save(gjsj);
			return false;
		}
		//获取告警记录
		List gjList = gjDao.getGjRecordByRwid(rwid);
		if(gjList.isEmpty()){
			logger.info("告警失败！该任务未设置告警信息.");
			gjsj.setFd_gjbz("1");
			gjsjDao.save(gjsj);
			return false;
		}
		//业务逻辑是一个任务只能建立一次告警，所以这里提取第一个告警记录
		Map<String,Object> g = (Map<String,Object>)gjList.get(0);
		//组装gjsj实例
		gjsj.setFd_gjnr(content);
		gjsj.setFd_yh_id((String)((Map<String,Object>)g).get("FD_YH_ID"));
		gjsj.setFd_rwid(rwid);
		
		//发送告警信息
		if(Conts.GJ_MESSAGE.equals(g.get("FD_GJFS"))){
			//发送短信 TODO
			logger.info("短信告警");
		}else if(Conts.GJ_EMAIL.equals(g.get("FD_GJFS"))){
			//发送邮件 TODO
			logger.info("邮件告警");
		}
		gjsj.setFd_gjbz("0");
		gjsjDao.save(gjsj);
		return true;
	}
}
