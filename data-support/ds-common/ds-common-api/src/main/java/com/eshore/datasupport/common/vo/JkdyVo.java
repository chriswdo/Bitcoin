package com.eshore.datasupport.common.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DSJZC_JKDY")
public class JkdyVo  {

	private String id 				;
	private String jk_code          ;
	private String jk_name          ;
	private String sjy_id           ;
	private String sjy_lx           ;
	private String page_pre         ;
	private String page_suf         ;
	private String sql_info         ;
	private String keywords         ;
	private String jk_zt	        ;
	private String fd_gxr           ;
	private String fd_jkbz           ;
	private String fd_mc           ;
	private Date fd_gxsj          ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJk_code() {
		return jk_code;
	}
	public void setJk_code(String jk_code) {
		this.jk_code = jk_code;
	}
	public String getJk_name() {
		return jk_name;
	}
	public void setJk_name(String jk_name) {
		this.jk_name = jk_name;
	}
	public String getSjy_id() {
		return sjy_id;
	}
	public void setSjy_id(String sjy_id) {
		this.sjy_id = sjy_id;
	}
	public String getSjy_lx() {
		return sjy_lx;
	}
	public void setSjy_lx(String sjy_lx) {
		this.sjy_lx = sjy_lx;
	}
	public String getPage_pre() {
		return page_pre;
	}
	public void setPage_pre(String page_pre) {
		this.page_pre = page_pre;
	}
	public String getPage_suf() {
		return page_suf;
	}
	public void setPage_suf(String page_suf) {
		this.page_suf = page_suf;
	}
	public String getSql_info() {
		return sql_info;
	}
	public void setSql_info(String sql_info) {
		this.sql_info = sql_info;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getJk_zt() {
		return jk_zt;
	}
	public void setJk_zt(String jk_zt) {
		this.jk_zt = jk_zt;
	}
	public String getFd_gxr() {
		return fd_gxr;
	}
	public void setFd_gxr(String fd_gxr) {
		this.fd_gxr = fd_gxr;
	}
	public String getFd_jkbz() {
		return fd_jkbz;
	}
	public void setFd_jkbz(String fd_jkbz) {
		this.fd_jkbz = fd_jkbz;
	}

	public String getFd_mc() {
		return fd_mc;
	}
	public void setFd_mc(String fd_mc) {
		this.fd_mc = fd_mc;
	}
	public Date getFd_gxsj() {
		return fd_gxsj;
	}
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj = fd_gxsj;
	}
	

	
}
