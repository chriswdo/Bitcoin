package com.eshore.datasupport.common.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DSJZC_GJSJ")
public class Gjsj implements Serializable{
	private String id;//id
	private String fd_yh_id;//用户id
	private Date fd_gjsj;//告警时间
	private String fd_gjnr;//告警内容
	private String fd_rwid;//任务id
	private String fd_gjbz;//告警标志
	
	@Id
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_YH_ID",length=40)
	public String getFd_yh_id(){
	  		return fd_yh_id;
	}
	
	public void setFd_yh_id(String fd_yh_id) {
		this.fd_yh_id = fd_yh_id;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_GJSJ",length=20)
	public Date getFd_gjsj(){
	  		return fd_gjsj==null?null:(Date)fd_gjsj.clone();
	}
	
	public void setFd_gjsj(Date fd_gjsj) {
		this.fd_gjsj = (Date)fd_gjsj.clone();
	}		
	
	@Column(name="FD_GJNR",length=2000)
	public String getFd_gjnr(){
	  		return fd_gjnr;
	}
	
	public void setFd_gjnr(String fd_gjnr) {
		this.fd_gjnr = fd_gjnr;
	}		
	
	@Column(name="FD_RWID",length=40)
	public String getFd_rwid(){
	  		return fd_rwid;
	}
	
	public void setFd_rwid(String fd_rwid) {
		this.fd_rwid = fd_rwid;
	}		
	
	@Column(name="FD_GJBZ",length=2)
	public String getFd_gjbz(){
	  		return fd_gjbz;
	}
	
	public void setFd_gjbz(String fd_gjbz) {
		this.fd_gjbz = fd_gjbz;
	}		
}

