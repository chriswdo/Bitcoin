package com.eshore.datasupport.task.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DSJZC_RWSL")
public class Rwsl implements Serializable{
	private String id;//实例ID
	private String fd_rw_id;//任务ID
	private Date fd_qdsj;//任务启动时间
	private Date fd_jssj;//任务结束时间
	private String fd_yxcs;//数据结构 json:{SJSJ_KS:xxx,SJSJ_JS:xxx}
	private Integer fd_zxjg;//执行结果：0 失败，1 成功
	private Long fd_cljls;//处理记录数
	private Long fd_ycjls;//异常记录数
	private byte[] fd_bzxx;//备注信息
	private String fd_sjzt;//数据状态：N 失效，Y 有效
	private Date fd_sjc;//时间戳
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
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_QDSJ")
	public Date getFd_qdsj(){
	  		return fd_qdsj==null?null:(Date)(fd_qdsj.clone());
	}
	
	public void setFd_qdsj(Date fd_qdsj) {
		this.fd_qdsj =fd_qdsj==null?null: (Date)(fd_qdsj.clone());
	}		
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_JSSJ")
	public Date getFd_jssj(){
	  		return fd_jssj==null?null:(Date)(fd_jssj.clone());
	}
	
	public void setFd_jssj(Date fd_jssj) {
		this.fd_jssj = fd_jssj==null?null:(Date)(fd_jssj.clone());
	}		
	
	@Column(name="FD_YXCS",length=200)
	public String getFd_yxcs(){
	  		return fd_yxcs;
	}
	
	public void setFd_yxcs(String fd_yxcs) {
		this.fd_yxcs = fd_yxcs;
	}		
	
	@Column(name="FD_ZXJG")
	public Integer getFd_zxjg(){
	  		return fd_zxjg;
	}
	
	public void setFd_zxjg(Integer fd_zxjg) {
		this.fd_zxjg = fd_zxjg;
	}		
	
	@Column(name="FD_CLJLS")
	public Long getFd_cljls(){
	  		return fd_cljls;
	}
	
	public void setFd_cljls(Long fd_cljls) {
		this.fd_cljls = fd_cljls;
	}		
	
	@Column(name="FD_YCJLS")
	public Long getFd_ycjls(){
	  		return fd_ycjls;
	}
	
	public void setFd_ycjls(Long fd_ycjls) {
		this.fd_ycjls = fd_ycjls;
	}		

	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="FD_BZXX",columnDefinition = "BLOB")
	public byte[] getFd_bzxx(){
		if(fd_bzxx!=null){
			return Arrays.copyOf(fd_bzxx, fd_bzxx.length);
		}
			return new byte[0];	  		 
	}
	
	public void setFd_bzxx(byte[] fd_bzxx) {
		if(fd_bzxx!=null){
			this.fd_bzxx = Arrays.copyOf(fd_bzxx, fd_bzxx.length);
		}else{
			this.fd_bzxx=null;
		}
	}	
	
	@Column(name="FD_SJZT",length=2)
	public String getFd_sjzt(){
	  		return fd_sjzt;
	}
	
	public void setFd_sjzt(String fd_sjzt) {
		this.fd_sjzt = fd_sjzt;
	}		
	@Temporal(value = TemporalType.TIMESTAMP)  
	@Column(name="FD_SJC")
	public Date getFd_sjc(){
	  		return fd_sjc==null?null:(Date)(fd_sjc.clone());
	}
	
	public void setFd_sjc(Date fd_sjc) {
		this.fd_sjc = fd_sjc==null?null:(Date)(fd_sjc.clone());
	}		
	
}

