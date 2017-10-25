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
@Table(name="DSJZC_YSJB")
public class Ysjb implements Serializable{
	private String id;//主键
	private String fd_bm;//表名
	private String fd_ms;//描述
	private String fd_sjy_id;//数据源主键
	private Date fd_tbsj;//同步时间
	private String fd_tbms;//元数据表最近一次同步字段情况
	private String fd_tbr;//元数据表的同步操作人标识
	private Date fd_cjsj;//创建时间
	private String fd_cjr;//创建人标识
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_BM",length=255)
	public String getFd_bm(){
	  		return fd_bm;
	}
	
	public void setFd_bm(String fd_bm) {
		this.fd_bm = fd_bm;
	}		
	
	@Column(name="FD_SJY_ID",length=40)
	public String getFd_sjy_id(){
	  		return fd_sjy_id;
	}
	
	public void setFd_sjy_id(String fd_sjy_id) {
		this.fd_sjy_id = fd_sjy_id;
	}		
	
	@Column(name="FD_MS",length=1000)
	public String getFd_ms(){
	  		return fd_ms;
	}
	
	public void setFd_ms(String fd_ms) {
		if(fd_ms==null){
			this.fd_ms = "";
		}else{
			this.fd_ms = fd_ms;		
		}
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_TBSJ")
	public Date getFd_tbsj(){
	    if(fd_tbsj == null) {
	        return null;
	    }
	    return (Date) fd_tbsj.clone();  
	}
	
	public void setFd_tbsj(Date fd_tbsj) {
	    if(fd_tbsj == null) {
	        this.fd_tbsj = null;
	    }else {
	        this.fd_tbsj = (Date) fd_tbsj.clone();
	    }  
	}		
	
	@Column(name="FD_TBMS",length=2000)
	public String getFd_tbms(){
	  		return fd_tbms;
	}
	
	public void setFd_tbms(String fd_tbms) {
		this.fd_tbms = fd_tbms;
	}		
	
	@Column(name="FD_TBR",length=40)
	public String getFd_tbr(){
	  		return fd_tbr;
	}
	
	public void setFd_tbr(String fd_tbr) {
		this.fd_tbr = fd_tbr;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_CJSJ")
	public Date getFd_cjsj(){
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
	
	@Column(name="FD_CJR",length=40)
	public String getFd_cjr(){
	  		return fd_cjr;
	}
	
	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}		
}

