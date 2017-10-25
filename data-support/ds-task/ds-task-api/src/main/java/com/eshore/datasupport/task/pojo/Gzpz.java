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
@Table(name="DSJZC_GZPZ")
public class Gzpz implements Serializable{
	private String id;//主键
	private Integer fd_gzlx;//规则类型：0 采集，1 处理，2挖掘
	private String fd_gzmc;//规则名称
	private Integer r_job_id;//kettle 任务ID
	private String r_job_name;//kettle 任务名称
	private String r_job_desc;//kettle 任务描述
	private String fd_srsjy_id;//输入数据源ID
	private String fd_srb_id;//输入表ID
	private String fd_scsjy_id;//输出数据源ID
	private String fd_scb_id;//输出数表ID
	private String fd_gxr;//更新人
	private String fd_sjzt;//数据状态：N 失效，Y 有效
	private Date fd_gxsj;//时间戳
	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_GZLX")
	public Integer getFd_gzlx(){
	  		return fd_gzlx;
	}
	
	public void setFd_gzlx(Integer fd_gzlx) {
		this.fd_gzlx = fd_gzlx;
	}		
	
	@Column(name="FD_GZMC",length=100)
	public String getFd_gzmc(){
	  		return fd_gzmc;
	}
	
	public void setFd_gzmc(String fd_gzmc) {
		this.fd_gzmc = fd_gzmc;
	}		
	
	@Column(name="R_JOB_ID")
	public Integer getR_job_id(){
	  		return r_job_id;
	}
	
	public void setR_job_id(Integer r_job_id) {
		this.r_job_id = r_job_id;
	}		
	
	@Column(name="R_JOB_NAME",length=100)
	public String getR_job_name(){
	  		return r_job_name;
	}
	
	public void setR_job_name(String r_job_name) {
		this.r_job_name = r_job_name;
	}		
	
	@Column(name="R_JOB_DESC",length=255)
	public String getR_job_desc(){
	  		return r_job_desc;
	}
	
	public void setR_job_desc(String r_job_desc) {
		this.r_job_desc = r_job_desc;
	}		
	
	@Column(name="FD_SRSJY_ID",length=40)
	public String getFd_srsjy_id(){
	  		return fd_srsjy_id;
	}
	
	public void setFd_srsjy_id(String fd_srsjy_id) {
		this.fd_srsjy_id = fd_srsjy_id;
	}		
	
	@Column(name="FD_SRB_ID",length=40)
	public String getFd_srb_id(){
	  		return fd_srb_id;
	}
	
	public void setFd_srb_id(String fd_srb_id) {
		this.fd_srb_id = fd_srb_id;
	}		
	
	@Column(name="FD_SCSJY_ID",length=40)
	public String getFd_scsjy_id(){
	  		return fd_scsjy_id;
	}
	
	public void setFd_scsjy_id(String fd_scsjy_id) {
		this.fd_scsjy_id = fd_scsjy_id;
	}		
	
	@Column(name="FD_SCB_ID",length=40)
	public String getFd_scb_id(){
	  		return fd_scb_id;
	}
	
	public void setFd_scb_id(String fd_scb_id) {
		this.fd_scb_id = fd_scb_id;
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
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_GXSJ")
	public Date getFd_gxsj(){
	  		return fd_gxsj==null?null: (Date)(fd_gxsj.clone());
	}
	
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj =fd_gxsj==null?null: (Date)(fd_gxsj.clone());
	}		
}

