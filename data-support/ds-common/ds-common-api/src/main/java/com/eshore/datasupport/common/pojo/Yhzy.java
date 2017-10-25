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
@Table(name="DSJZC_YHZY")
public class Yhzy implements Serializable{
	private String id;//id
	private String fd_yhid;//用户id
	private String fd_zyid;//资源id
	private Date fd_cjsj;//创建时间
	private String fd_cjr;//创建人
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_YHID",length=40)
	public String getFd_yhid(){
	  		return fd_yhid;
	}
	
	public void setFd_yhid(String fd_yhid) {
		this.fd_yhid = fd_yhid;
	}		
	
	@Column(name="FD_ZYID",length=40)
	public String getFd_zyid(){
	  		return fd_zyid;
	}
	
	public void setFd_zyid(String fd_zyid) {
		this.fd_zyid = fd_zyid;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_CJSJ")
	public Date getFd_cjsj(){
	  		return fd_cjsj==null?null:(Date)(fd_cjsj.clone());
	}
	
	public void setFd_cjsj(Date fd_cjsj) {
		this.fd_cjsj = fd_cjsj==null?null:(Date)(fd_cjsj.clone());
	}		
	
	@Column(name="FD_CJR",length=40)
	public String getFd_cjr(){
	  		return fd_cjr;
	}
	
	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}		
}

