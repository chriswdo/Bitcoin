package com.eshore.datasupport.common.vo;

import java.util.Date;

public class JkVo {
	/*接口定义字段*/
	private String id 				;
	private String jk_code          ;
	private String jk_name          ;
	private String sjy_id           ;
	private String page_pre         ;
	private String page_suf         ;
	private String sql_info         ;
	private String keywords         ;
	private String jk_zt	        ;
	private String fd_gxr           ;
	private Date fd_gxsj          ;
	
	/*数据源字段*/
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
	public Date getFd_gxsj() {
		return fd_gxsj;
	}
	public void setFd_gxsj(Date fd_gxsj) {
		this.fd_gxsj = fd_gxsj;
	}
	public String getFd_mc() {
		return fd_mc;
	}
	public void setFd_mc(String fd_mc) {
		this.fd_mc = fd_mc;
	}
	public String getFd_lx() {
		return fd_lx;
	}
	public void setFd_lx(String fd_lx) {
		this.fd_lx = fd_lx;
	}
	public String getFd_sjkmc() {
		return fd_sjkmc;
	}
	public void setFd_sjkmc(String fd_sjkmc) {
		this.fd_sjkmc = fd_sjkmc;
	}
	public String getFd_ip() {
		return fd_ip;
	}
	public void setFd_ip(String fd_ip) {
		this.fd_ip = fd_ip;
	}
	public Integer getFd_dk() {
		return fd_dk;
	}
	public void setFd_dk(Integer fd_dk) {
		this.fd_dk = fd_dk;
	}
	public Long getFd_kid() {
		return fd_kid;
	}
	public void setFd_kid(Long fd_kid) {
		this.fd_kid = fd_kid;
	}
	public String getFd_sjklx() {
		return fd_sjklx;
	}
	public void setFd_sjklx(String fd_sjklx) {
		this.fd_sjklx = fd_sjklx;
	}
	public String getFd_ms() {
		return fd_ms;
	}
	public void setFd_ms(String fd_ms) {
		this.fd_ms = fd_ms;
	}
	public String getFd_yhm() {
		return fd_yhm;
	}
	public void setFd_yhm(String fd_yhm) {
		this.fd_yhm = fd_yhm;
	}
	public String getFd_mm() {
		return fd_mm;
	}
	public void setFd_mm(String fd_mm) {
		this.fd_mm = fd_mm;
	}
	public Date getFd_cjsj() {
		return fd_cjsj;
	}
	public void setFd_cjsj(Date fd_cjsj) {
		this.fd_cjsj = fd_cjsj;
	}
	public String getFd_cjr() {
		return fd_cjr;
	}
	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}
	
}
