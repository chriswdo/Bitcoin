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
@Table(name="DSJZC_YHB")
public class Yhb implements Serializable{
	private String id;//id
	private String fd_yhmc;//用户名称
	private String fd_dlmc;//登录名称
	private String fd_lxfs;//联系方式
	private String fd_yx;//邮箱
	private String fd_mm;//密码
	private String fd_zt;//状态
	private Date fd_cjsj;//创建时间
	private Date fd_gxsj;//更新时间
	private String fd_cjr;//创建人
	private String fd_gxr;//更新人
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_YHMC",length=255)
	public String getFd_yhmc(){
	  		return fd_yhmc;
	}
	
	public void setFd_yhmc(String fd_yhmc) {
		this.fd_yhmc = fd_yhmc;
	}		
	
	@Column(name="FD_DLMC",length=255)
	public String getFd_dlmc(){
	  		return fd_dlmc;
	}
	
	public void setFd_dlmc(String fd_dlmc) {
		this.fd_dlmc = fd_dlmc;
	}		
	
	@Column(name="FD_LXFS",length=20)
	public String getFd_lxfs(){
	  		return fd_lxfs;
	}
	
	public void setFd_lxfs(String fd_lxfs) {
		this.fd_lxfs = fd_lxfs;
	}		
	
	@Column(name="FD_YX",length=40)
	public String getFd_yx(){
	  		return fd_yx;
	}
	
	public void setFd_yx(String fd_yx) {
		this.fd_yx = fd_yx;
	}		
	
	@Column(name="FD_MM",length=255)
	public String getFd_mm(){
	  		return fd_mm;
	}
	
	public void setFd_mm(String fd_mm) {
		this.fd_mm = fd_mm;
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
	  		return fd_cjsj==null?null:(Date)(fd_cjsj.clone());
	}
	
	public void setFd_cjsj(Date fd_cjsj) {
		this.fd_cjsj = fd_cjsj==null?null:(Date)(fd_cjsj.clone());
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_GXSJ")
	public Date getFd_gxsj(){
	  		return fd_gxsj==null?null:(Date)(fd_gxsj.clone());
	}
	
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj = fd_gxsj==null?null:(Date)(fd_gxsj.clone());
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
}

