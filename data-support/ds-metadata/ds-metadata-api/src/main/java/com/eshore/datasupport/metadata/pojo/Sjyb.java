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
@Table(name="DSJZC_SJYB")
public class Sjyb implements Serializable{
	private String id;//id
	private String fd_mc;//数据源名称
	private String fd_lx;//数据源类型  1子交换库  2平台交换库   3平台共享库 4 平台仓库  5平台集市
	private String fd_sjkmc;//数据库名称
	private String fd_ip;//数据源IP
	private Integer fd_dk;//数据源端口
	private Long fd_kid;//kettle数据源id
	private String fd_sjklx;//数据库类型mysql,oracle,db2,greenplum
	private String fd_ms;//数据源描述
	private String fd_yhm;//数据源用户名称
	private String fd_mm;//数据源密码  密文需要加密
	private Date fd_cjsj;//数据源记录的创建时间
	private String fd_cjr;//数据源记录的创建人标识
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_MC",length=255)
	public String getFd_mc(){
	  		return fd_mc;
	}
	
	public void setFd_mc(String fd_mc) {
		this.fd_mc = fd_mc;
	}		
	
	@Column(name="FD_LX",length=2)
	public String getFd_lx(){
	  		return fd_lx;
	}
	
	public void setFd_lx(String fd_lx) {
		this.fd_lx = fd_lx;
	}		
	
	@Column(name="FD_sjkmc",length=255)
	public String getFd_sjkmc(){
	  		return fd_sjkmc;
	}
	
	public void setFd_sjkmc(String fd_sjkmc) {
		this.fd_sjkmc = fd_sjkmc;
	}		
	
	@Column(name="FD_IP",length=15)
	public String getFd_ip(){
	  		return fd_ip;
	}
	
	public void setFd_ip(String fd_ip) {
		this.fd_ip = fd_ip;
	}		
	
	@Column(name="FD_DK")
	public Integer getFd_dk(){
	  		return fd_dk;
	}
	
	public void setFd_dk(Integer fd_dk) {
		this.fd_dk = fd_dk;
	}		
	
	@Column(name="FD_KID")
	public Long getFd_kid(){
	  		return fd_kid;
	}
	
	public void setFd_kid(Long fd_kid) {
		this.fd_kid = fd_kid;
	}		
	
	@Column(name="FD_SJKLX",length=20)
	public String getFd_sjklx(){
	  		return fd_sjklx;
	}
	
	public void setFd_sjklx(String fd_sjklx) {
		this.fd_sjklx = fd_sjklx;
	}		
	
	@Column(name="FD_MS",length=1000)
	public String getFd_ms(){
	  		return fd_ms;
	}
	
	public void setFd_ms(String fd_ms) {
		this.fd_ms = fd_ms;
	}		
	
	@Column(name="FD_YHM",length=255)
	public String getFd_yhm(){
	  		return fd_yhm;
	}
	
	public void setFd_yhm(String fd_yhm) {
		this.fd_yhm = fd_yhm;
	}		
	
	@Column(name="FD_MM",length=255)
	public String getFd_mm(){
	  		return fd_mm;
	}
	
	public void setFd_mm(String fd_mm) {
		this.fd_mm = fd_mm;
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

