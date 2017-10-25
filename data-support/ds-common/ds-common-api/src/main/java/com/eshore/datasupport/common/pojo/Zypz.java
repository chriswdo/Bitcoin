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
@Table(name="DSJZC_ZYPZ")
public class Zypz implements Serializable{
	private String id     ;//id
	private String fd_fjid;//父级id
	private String fd_mc  ;//名称
	private String fd_dz  ;//地址
	private String fd_tb  ;//图标
	private Integer fd_xh  ;//排序
	private Date fd_cjsj;//创建时间
	private Date fd_gxsj;//更新时间

	private String fd_cjr ;//创建人
	private String fd_gxr ;//更新人
	@Id
	
	@Column(name="ID",length=40)
	public String getId     (){
	  		return id     ;
	}
	
	public void setId     (String id     ) {
		this.id      = id     ;
	}		
	
	@Column(name="FD_FJID",length=40)
	public String getFd_fjid(){
	  		return fd_fjid;
	}
	
	public void setFd_fjid(String fd_fjid) {
		this.fd_fjid = fd_fjid;
	}		
	
	@Column(name="FD_MC",length=255)
	public String getFd_mc  (){
	  		return fd_mc  ;
	}
	
	public void setFd_mc  (String fd_mc  ) {
		this.fd_mc   = fd_mc  ;
	}		
	
	@Column(name="FD_DZ",length=512)
	public String getFd_dz  (){
	  		return fd_dz  ;
	}
	
	public void setFd_dz  (String fd_dz  ) {
		this.fd_dz   = fd_dz  ;
	}		
	
	@Column(name="FD_TB",length=64)
	public String getFd_tb  (){
	  		return fd_tb  ;
	}
	
	public void setFd_tb  (String fd_tb  ) {
		this.fd_tb   = fd_tb  ;
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
	public String getFd_cjr (){
	  		return fd_cjr ;
	}
	
	public void setFd_cjr (String fd_cjr ) {
		this.fd_cjr  = fd_cjr ;
	}		
	
	@Column(name="FD_GXR",length=40)
	public String getFd_gxr (){
	  		return fd_gxr ;
	}
	
	public void setFd_gxr (String fd_gxr ) {
		this.fd_gxr  = fd_gxr ;
	}		
	
	@Column(name="FD_XH")
	public Integer getFd_xh() {
		return fd_xh;
	}

	public void setFd_xh(Integer fd_xh) {
		this.fd_xh = fd_xh;
	}
}

