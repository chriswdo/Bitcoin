package com.eshore.datasupport.metadata.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DSJZC_XXFL")
public class Xxfl implements Serializable {
	private String id;// id
	private String fd_xxmc;// 信息名称
	private String fd_ztid;// 主题ID
	private String fd_bm;// 表名
	private String fd_ms;// 描述
	private Date fd_cjsj;// 创建时间
	private String fd_gxr;// 数据字典更新人标识
	private Date fd_gxsj;// 数据字典更新时间
	private String fd_cjr;// 创建人标识

	@Id
	@Column(name = "ID", length = 40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    
	@Column(name = "FD_MS", length = 1000)
	public String getFd_ms() {
		return fd_ms;
	}

	public void setFd_ms(String fd_ms) {
		this.fd_ms = fd_ms;
	}

	

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "FD_CJSJ")
	public Date getFd_cjsj() {
	    if(fd_cjsj == null) {
	        return null;
	    }
	    return (Date) fd_cjsj.clone();  
	}

	public void setFd_cjsj(Date fd_cjsj) {
	    if(fd_cjsj == null) {
	        this.fd_cjsj = null;
	    }else {
	        this.fd_cjsj = (Date) fd_cjsj.clone();
	    }  
	}

	@Column(name = "FD_CJR", length = 40)
	public String getFd_cjr() {
		return fd_cjr;
	}

	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}
	@Column(name = "FD_XXMC", length = 255)
	public String getFd_xxmc() {
		return fd_xxmc;
	}

	public void setFd_xxmc(String fd_xxmc) {
		this.fd_xxmc = fd_xxmc;
	}
	@Column(name = "FD_ZTID", length = 40)
	public String getFd_ztid() {
		return fd_ztid;
	}

	public void setFd_ztid(String fd_ztid) {
		this.fd_ztid = fd_ztid;
	}
	@Column(name = "FD_BM", length = 255)
	public String getFd_bm() {
		return fd_bm;
	}

	public void setFd_bm(String fd_bm) {
		this.fd_bm = fd_bm;
	}
	@Column(name = "FD_GXR", length = 40)
	public String getFd_gxr() {
		return fd_gxr;
	}

	public void setFd_gxr(String fd_gxr) {
		this.fd_gxr = fd_gxr;
	}
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "FD_GXSJ")
	public Date getFd_gxsj() {
	    if(fd_gxsj == null) {
	        return null;
	    }
	    return (Date) fd_gxsj.clone();  
	}

	public void setFd_gxsj(Date fd_gxsj) {
	    if(fd_gxsj == null) {
	        this.fd_gxsj = null;
	    }else {
	        this.fd_gxsj = (Date) fd_gxsj.clone();
	    }  
	}
	
	
}
