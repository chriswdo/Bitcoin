package com.eshore.datasupport.task.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DSJZC_RWB")
public class Rwb implements Serializable{
	private String id;//id
	private String fd_rwbh;//任务编号
	private Byte fd_lx;//规则类型：0 采集，1 处理，2挖掘
	private String fd_gz_id;
	private String fd_ddpl;//调度频率（Cron表达式）
	private Date fd_scqdsj;//首次启动时间
	private String fd_rwzt;//任务状态
	private Date fd_sjsjc;//数据时间戳
	private Integer fd_sjl;//单次任务执行处理的数据量
	private String fd_sjldw;//数据量单位：分钟/小时/天/周/月/年，默认分钟
	private String fd_rwms;//任务描述
	private String fd_gxr;//更新人
	private String fd_sjzt;//数据状态：N 失效，Y 有效
	private Date fd_gxsj;//更新时间
	private String fd_zntz;//智能调整
	
	private String fd_qzrw_id;//前置任务
	
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_RWBH",length=64)
	public String getFd_rwbh(){
	  		return fd_rwbh;
	}
	
	public void setFd_rwbh(String fd_rwbh) {
		this.fd_rwbh = fd_rwbh;
	}		
	
	@Column(name="FD_LX")
	public Byte getFd_lx(){
	  		return fd_lx;
	}
	
	public void setFd_lx(Byte fd_lx) {
		this.fd_lx = fd_lx;
	}		
	
	@Column(name="FD_DDPL",length=50)
	public String getFd_ddpl(){
	  		return fd_ddpl;
	}
	
	public void setFd_ddpl(String fd_ddpl) {
		this.fd_ddpl = fd_ddpl;
	}		
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_SCQDSJ")
	public Date getFd_scqdsj(){
	  		return fd_scqdsj==null?null:(Date)fd_scqdsj.clone();
	}
	
	public void setFd_scqdsj(Date fd_scqdsj) {
		this.fd_scqdsj = fd_scqdsj==null?null:(Date)(fd_scqdsj.clone());
	}		
	
	@Column(name="FD_RWZT",length=2)
	public String getFd_rwzt(){
	  		return fd_rwzt;
	}
	
	public void setFd_rwzt(String fd_rwzt) {
		this.fd_rwzt = fd_rwzt;
	}		
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_SJSJC")
	public Date getFd_sjsjc(){
	  		return fd_sjsjc==null?null:(Date)(fd_sjsjc.clone());
	}
	
	public void setFd_sjsjc(Date fd_sjsjc) {
		this.fd_sjsjc = fd_sjsjc==null?null:(Date)(fd_sjsjc.clone());
	}		
	
	@Column(name="FD_SJL")
	public Integer getFd_sjl(){
	  		return fd_sjl;
	}
	
	public void setFd_sjl(Integer fd_sjl) {
		this.fd_sjl = fd_sjl;
	}		
	
	@Column(name="FD_SJLDW",length=4)
	public String getFd_sjldw(){
	  		return fd_sjldw;
	}
	
	public void setFd_sjldw(String fd_sjldw) {
		this.fd_sjldw = fd_sjldw;
	}		
	
	@Column(name="FD_RWMS",length=2000)
	public String getFd_rwms(){
	  		return fd_rwms;
	}
	
	public void setFd_rwms(String fd_rwms) {
		this.fd_rwms = fd_rwms;
	}		
	
	@Column(name="FD_GXR",length=40)
	public String getFd_gxr(){
	  		return fd_gxr;
	}
	
	public void setFd_gxr(String fd_gxr) {
		this.fd_gxr = fd_gxr;
	}		
	
	@Column(name="FD_SJZT",length=2)
	public String getFd_sjzt(){
	  		return fd_sjzt;
	}
	
	public void setFd_sjzt(String fd_sjzt) {
		this.fd_sjzt = fd_sjzt;
	}		
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_GXSJ")
	public Date getFd_gxsj(){
	  		return fd_gxsj==null?null:(Date)(fd_gxsj.clone());
	}
	
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj = fd_gxsj==null?null:(Date)(fd_gxsj.clone());
	}		
	
	@Column(name="FD_ZNTZ",length=2)
	public String getFd_zntz(){
	  		return fd_zntz;
	}
	
	public void setFd_zntz(String fd_zntz) {
		this.fd_zntz = fd_zntz;
	}
	@Column(name="FD_QZRW_ID",length=40)
	public String getFd_qzrw_id() {
		return fd_qzrw_id;
	}

	public void setFd_qzrw_id(String fd_qzrw_id) {
		this.fd_qzrw_id = fd_qzrw_id;
	}
	@Column(name="FD_GZ_ID",length=40)
	public String getFd_gz_id() {
		return fd_gz_id;
	}

	public void setFd_gz_id(String fd_gz_id) {
		this.fd_gz_id = fd_gz_id;
	}		
	
	
}

