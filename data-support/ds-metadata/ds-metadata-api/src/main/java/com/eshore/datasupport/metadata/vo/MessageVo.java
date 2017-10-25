package com.eshore.datasupport.metadata.vo;

public class MessageVo {
	private String fd_ztmc; //信息主题名称
	private String zid;//信息主题ID
	private String fd_xxmc;//信息名称
	private String fd_bm;//信息表名
	private String fd_ms;//信息描述
	private String id;//信息表ID
	public String getFd_ztmc() {
		return fd_ztmc;
	}
	public void setFd_ztmc(String fd_ztmc) {
		if(fd_ztmc==null){
			this.fd_ztmc="";
		}else{
			this.fd_ztmc = fd_ztmc;
		}
	}
	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getFd_xxmc() {
		return fd_xxmc;
	}
	public void setFd_xxmc(String fd_xxmc) {
		
		if(fd_xxmc==null){
			this.fd_xxmc="";
		}else{
			this.fd_xxmc = fd_xxmc;
		}
	}
	public String getFd_bm() {
		return fd_bm;
	}
	public void setFd_bm(String fd_bm) {
		this.fd_bm = fd_bm;
	}
	public String getFd_ms() {
		return fd_ms;
	}
	public void setFd_ms(String fd_ms) {
		if(fd_ms==null){
			this.fd_ms="";
		}else{
			this.fd_ms = fd_ms;
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}




	
	
	
}
