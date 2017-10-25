package com.eshore.datasupport.task.vo;

import java.util.Date;

public class ExceptionRecordVo {
	private String id;//异常记录ID
	private String fd_srb_id;//异常记录产生的输入表ID
	private String fd_srsjy_id;//异常记录产生的数据源ID
	private Date fd_jssj;//异常记录结束时间
	private String fd_bz;//异常记录备注
	private String fd_srb_name;//异常记录产生的输入表ID对应的名称
	private String fd_srsjy_name;//异常记录产生的数据源ID对应的名称
	private String fd_jssjTostring;//异常记录时间的string格式
	public String getFd_srb_name() {
		return fd_srb_name;
	}
	public void setFd_srb_name(String fd_srb_name) {
		this.fd_srb_name = fd_srb_name;
	}
	public String getFd_srsjy_name() {
		return fd_srsjy_name;
	}
	public void setFd_srsjy_name(String fd_srsjy_name) {
		this.fd_srsjy_name = fd_srsjy_name;
	}
	public String getFd_jssjTostring() {
		return fd_jssjTostring;
	}
	public void setFd_jssjTostring(String fd_jssjTostring) {
		this.fd_jssjTostring = fd_jssjTostring;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFd_srb_id() {
		return fd_srb_id;
	}
	public void setFd_srb_id(String fd_srb_id) {
		this.fd_srb_id = fd_srb_id;
	}
	public String getFd_srsjy_id() {
		return fd_srsjy_id;
	}
	public void setFd_srsjy_id(String fd_srsjy_id) {
		this.fd_srsjy_id = fd_srsjy_id;
	}
	public Date getFd_jssj(){
  		return fd_jssj==null?null:(Date)(fd_jssj.clone());
}

    public void setFd_jssj(Date fd_jssj) {
	this.fd_jssj = fd_jssj==null?null:(Date)(fd_jssj.clone());
}	
	public String getFd_bz() {
		return fd_bz;
	}
	public void setFd_bz(String fd_bz) {
		if(fd_bz==null){
			this.fd_bz = "";
		}else{
			this.fd_bz = fd_bz;		
		}
	}


	
}
