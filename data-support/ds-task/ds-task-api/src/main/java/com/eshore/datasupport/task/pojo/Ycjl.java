package com.eshore.datasupport.task.pojo;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="DSJZC_YCJL")
public class Ycjl implements Serializable{
	private String id;//id
	private String fd_rw_id;//任务ID
	private String fd_srsjy_id;//输入数据源ID
	private String fd_srb_id;//输入表ID
	private byte[] fd_ycsj;//原始异常数据
	private String fd_bz;//备注异常原因
	private Long  fd_sjqs;//数据缺失数
	private Long fd_sjwx;//数据无效数
	
	@Column(name="FD_SJQS")
	public Long getFd_sjqs() {
		return fd_sjqs;
	}

	public void setFd_sjqs(Long fd_sjqs) {
		this.fd_sjqs = fd_sjqs;
	}

	@Column(name="FD_SJWX")
	public Long getFd_sjwx() {
		return fd_sjwx;
	}

	public void setFd_sjwx(Long fd_sjwx) {
		this.fd_sjwx = fd_sjwx;
	}

	@Id
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_RW_ID",length=40)
	public String getFd_rw_id(){
	  		return fd_rw_id;
	}
	
	public void setFd_rw_id(String fd_rw_id) {
		this.fd_rw_id = fd_rw_id;
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
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="FD_YCSJ",columnDefinition = "BLOB")
	public byte[] getFd_ycsj(){
			if(fd_ycsj!=null){
				return  Arrays.copyOf(fd_ycsj, fd_ycsj.length);
			}
			return new byte[0];
	  		
	}
	
	public void setFd_ycsj(byte[] fd_ycsj) {
		if(fd_ycsj!=null){
			this.fd_ycsj = Arrays.copyOf(fd_ycsj, fd_ycsj.length);
		}else{
			this.fd_ycsj = null;
		}
		
	}		
		
	@Column(name="FD_BZ",length=255)
	public String getFd_bz(){
	  		return fd_bz;
	}
	
	public void setFd_bz(String fd_bz) {
		this.fd_bz = fd_bz;
	}		
}

