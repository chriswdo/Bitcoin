package com.eshore.datasupport.common.vo;



public class RuleConfigVo {
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
	private String fd_srsjy_name;//输入数据源名称
	private String fd_srb_name;//输入表名称
	private String fd_scsjy_name;//输出数据源名称
	private String fd_scb_name;//输出数表名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getFd_gzlx() {
		return fd_gzlx;
	}
	public void setFd_gzlx(Integer fd_gzlx) {
		this.fd_gzlx = fd_gzlx;
	}
	public String getFd_gzmc() {
		return fd_gzmc;
	}
	public void setFd_gzmc(String fd_gzmc) {
		this.fd_gzmc = fd_gzmc;
	}
	public Integer getR_job_id() {
		return r_job_id;
	}
	public void setR_job_id(Integer r_job_id) {
		this.r_job_id = r_job_id;
	}
	public String getR_job_name() {
		return r_job_name;
	}
	public void setR_job_name(String r_job_name) {
		this.r_job_name = r_job_name;
	}
	public String getR_job_desc() {
		return r_job_desc;
	}
	public void setR_job_desc(String r_job_desc) {
		this.r_job_desc = r_job_desc;
	}
	public String getFd_srsjy_id() {
		return fd_srsjy_id;
	}
	public void setFd_srsjy_id(String fd_srsjy_id) {
		this.fd_srsjy_id = fd_srsjy_id;
	}
	public String getFd_srb_id() {
		return fd_srb_id;
	}
	public void setFd_srb_id(String fd_srb_id) {
		this.fd_srb_id = fd_srb_id;
	}
	public String getFd_scsjy_id() {
		return fd_scsjy_id;
	}
	public void setFd_scsjy_id(String fd_scsjy_id) {
		this.fd_scsjy_id = fd_scsjy_id;
	}
	public String getFd_scb_id() {
		return fd_scb_id;
	}
	public void setFd_scb_id(String fd_scb_id) {
		this.fd_scb_id = fd_scb_id;
	}
	public String getFd_srsjy_name() {
		return fd_srsjy_name;
	}
	public void setFd_srsjy_name(String fd_srsjy_name) {
		this.fd_srsjy_name = fd_srsjy_name;
	}
	public String getFd_srb_name() {
		return fd_srb_name;
	}
	public void setFd_srb_name(String fd_srb_name) {
		this.fd_srb_name = fd_srb_name;
	}
	public String getFd_scsjy_name() {
		return fd_scsjy_name;
	}
	public void setFd_scsjy_name(String fd_scsjy_name) {
		this.fd_scsjy_name = fd_scsjy_name;
	}
	public String getFd_scb_name() {
		return fd_scb_name;
	}
	public void setFd_scb_name(String fd_scb_name) {
		this.fd_scb_name = fd_scb_name;
	}
	
	
}
