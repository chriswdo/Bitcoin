package com.eshore.datasupport.task.vo;

import java.util.Date;

public class TaskKettleVo {
	private String id;
	private String fd_rwbh;//任务编号
	private Integer fd_lx;//规则类型：0 采集，1 处理，2挖掘
	private String gzmc;//规则名称
	private Integer r_job_id;//kettle 任务ID
	private String r_job_name;//kettle 任务名称
	private String r_job_desc;//kettle 任务描述
	private String fd_ddpl;//调度频率（Cron表达式）
	private Date fd_scqdsj;//首次启动时间
	private String fd_rwzt;//任务状态
	private Date fd_sjsjc;//数据时间戳
	private Integer fd_sjl;//单次任务执行处理的数据量
	private String fd_sjldw;//数据量单位：分钟/小时/天/周/月/年，默认分钟
	private String fd_rwms;//任务描述	
	private String fd_zntz;//智能调整	
	private String fd_qzrw_id;//前置任务	
	private String fd_srsjy_id;//输入数据源ID
	private String fd_srb_id;//输入表ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFd_rwbh() {
		return fd_rwbh;
	}
	public void setFd_rwbh(String fd_rwbh) {
		this.fd_rwbh = fd_rwbh;
	}
	public Integer getFd_lx() {
		return fd_lx;
	}
	public void setFd_lx(Integer fd_lx) {
		this.fd_lx = fd_lx;
	}
	public String getGzmc() {
		return gzmc;
	}
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
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
	public String getFd_ddpl() {
		return fd_ddpl;
	}
	public void setFd_ddpl(String fd_ddpl) {
		this.fd_ddpl = fd_ddpl;
	}
	public Date getFd_scqdsj() {
		if (fd_scqdsj == null) {
			return null;
		}
		return (Date) fd_scqdsj.clone();
	}
	public void setFd_scqdsj(Date fd_scqdsj) {
		if (fd_scqdsj == null) {
			this.fd_scqdsj = null;
		} else {
			this.fd_scqdsj = (Date) fd_scqdsj.clone();
		}
	}
	public String getFd_rwzt() {
		return fd_rwzt;
	}
	public void setFd_rwzt(String fd_rwzt) {
		this.fd_rwzt = fd_rwzt;
	}
	public Date getFd_sjsjc() {
		if (fd_sjsjc == null) {
			return null;
		}
		return (Date) fd_sjsjc.clone();
	}
	public void setFd_sjsjc(Date fd_sjsjc) {
		if (fd_sjsjc == null) {
			this.fd_sjsjc = null;
		} else {
			this.fd_sjsjc = (Date) fd_sjsjc.clone();
		}
	}
	public Integer getFd_sjl() {
		return fd_sjl;
	}
	public void setFd_sjl(Integer fd_sjl) {
		this.fd_sjl = fd_sjl;
	}
	public String getFd_sjldw() {
		return fd_sjldw;
	}
	public void setFd_sjldw(String fd_sjldw) {
		this.fd_sjldw = fd_sjldw;
	}
	public String getFd_rwms() {
		return fd_rwms;
	}
	public void setFd_rwms(String fd_rwms) {
		this.fd_rwms = fd_rwms;
	}
	public String getFd_zntz() {
		return fd_zntz;
	}
	public void setFd_zntz(String fd_zntz) {
		this.fd_zntz = fd_zntz;
	}
	public String getFd_qzrw_id() {
		return fd_qzrw_id;
	}
	public void setFd_qzrw_id(String fd_qzrw_id) {
		this.fd_qzrw_id = fd_qzrw_id;
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
	
	
	
}
