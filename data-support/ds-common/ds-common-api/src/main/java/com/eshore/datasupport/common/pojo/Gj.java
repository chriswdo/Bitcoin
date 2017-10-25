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
@Table(name="DSJZC_GJ")
public class Gj implements Serializable{
	private String id;//id
	private String fd_rwid;//告警任务id
	private String fd_zt;//状态
	private Date fd_cjsj;//创建时间
	private Date fd_gxsj;//更新时间
	private String fd_cjr;//创建人
	private String fd_gxr;//更新人
	private String fd_gjfs;//告警方式
	private String fd_yh_id;//用户id
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_RWID",length=40)
	public String getFd_rwid(){
	  		return fd_rwid;
	}
	
	public void setFd_rwid(String fd_rwid) {
		this.fd_rwid = fd_rwid;
	}		
	
	@Column(name="FD_YH_ID",length=40)
	public String getFd_yh_id(){
	  		return fd_yh_id;
	}
	
	public void setFd_yh_id(String fd_yh_id) {
		this.fd_yh_id = fd_yh_id;
	}		
	
	@Column(name="FD_ZT",length=2)
	public String getFd_zt(){
	  		return fd_zt;
	}
	
	public void setFd_zt(String fd_zt) {
		this.fd_zt = fd_zt;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_CJSJ")
	public Date getFd_cjsj(){
	  		return fd_cjsj==null?null:new Date(fd_cjsj.getTime());
	}
	
	public void setFd_cjsj(Date fd_cjsj) {
		this.fd_cjsj = fd_cjsj==null?null:new Date(fd_cjsj.getTime());
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_GXSJ")
	public Date getFd_gxsj(){
	  		return fd_gxsj==null?null:new Date(fd_gxsj.getTime());
	}
	
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj = fd_gxsj==null?null:new Date(fd_gxsj.getTime());
	}		
	
	@Column(name="FD_CJR",length=40)
	public String getFd_cjr(){
	  		return fd_cjr;
	}
	
	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}		
	
	@Column(name="FD_GXR",length=40)
	public String getFd_gxr(){
	  		return fd_gxr;
	}
	
	public void setFd_gxr(String fd_gxr) {
		this.fd_gxr = fd_gxr;
	}		
	
	@Column(name="FD_GJFS",length=2)
	public String getFd_gjfs(){
	  		return fd_gjfs;
	}
	
	public void setFd_gjfs(String fd_gjfs) {
		this.fd_gjfs = fd_gjfs;
	}		
}

