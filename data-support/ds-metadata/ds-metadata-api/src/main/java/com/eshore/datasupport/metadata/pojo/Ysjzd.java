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
@Table(name="DSJZC_YSJZD")
public class Ysjzd implements Serializable{
	private String id;//id
	private String fd_zdmc;//元数据字段的字段名称
	private String fd_zdlx;//元数据字段的字段类型
	private Short fd_zdcd;//元数据字段的字段长度
	private Short fd_zdjd;//元数据字段的字段精度
	private String fd_sfzj;//元数据字段的是否主键   Y是; N否
	private String fd_qsz;//元数据字段的缺省值
	private String fd_sfkwk;//元数据字段的是否可为空   Y是;N否
	private String fd_zdbz;//源数据字段的字段描述
	private String fd_sjzd;//元数据字段的数据字典   配置用以对源表数据检查
	private String fd_gxr;//数据字典更新人标识
	private Date fd_gxsj;//数据字典更新时间
	private String fd_cjr;//创建人标识
	private Date fd_cjsj;//创建时间
	private String fd_ysjb_id;
	private Short fd_zdsx;
	
	@Column(name="FD_ZDSX")
	public Short getFd_zdsx() {
		return fd_zdsx;
	}

	public void setFd_zdsx(Short fd_zdsx) {
		this.fd_zdsx = fd_zdsx;
	}

	@Column(name="FD_YSJB_ID",length=255)
	public String getFd_ysjb_id() {
		return fd_ysjb_id;
	}
 
	public void setFd_ysjb_id(String fd_ysjb_id) {
		this.fd_ysjb_id = fd_ysjb_id;
	}

	@Id
	
	@Column(name="ID",length=40)
	public String getId(){
	  		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}		
	
	@Column(name="FD_ZDMC",length=255)
	public String getFd_zdmc(){
	  		return fd_zdmc;
	}
	
	public void setFd_zdmc(String fd_zdmc) {
		this.fd_zdmc = fd_zdmc;
	}		
	
	@Column(name="FD_ZDLX",length=32)
	public String getFd_zdlx(){
	  		return fd_zdlx;
	}
	
	public void setFd_zdlx(String fd_zdlx) {
		this.fd_zdlx = fd_zdlx;
	}		
	
	@Column(name="FD_ZDCD")
	public Short getFd_zdcd(){
	  		return fd_zdcd;
	}
	
	public void setFd_zdcd(Short fd_zdcd) {
		this.fd_zdcd = fd_zdcd;
	}

	@Column(name="FD_ZDJD")
	public Short getFd_zdjd(){
		return fd_zdjd;
	}

	public void setFd_zdjd(Short fd_zdjd) {
		this.fd_zdjd = fd_zdjd;
	}
	
	@Column(name="FD_SFZJ",length=1)
	public String getFd_sfzj(){
	  		return fd_sfzj;
	}
	
	public void setFd_sfzj(String fd_sfzj) {
		this.fd_sfzj = fd_sfzj;
	}		
	
	@Column(name="FD_QSZ",length=1000)
	public String getFd_qsz(){
	  		return fd_qsz;
	}
	
	public void setFd_qsz(String fd_qsz) {
		if(fd_qsz==null){
			this.fd_qsz = "";
		}else{
			this.fd_qsz = fd_qsz;
		}
	}		
	
	@Column(name="FD_SFKWK",length=1)
	public String getFd_sfkwk(){
	  		return fd_sfkwk;
	}
	
	public void setFd_sfkwk(String fd_sfkwk) {
		this.fd_sfkwk = fd_sfkwk;
	}		
	
	@Column(name="FD_ZDBZ",length=2000)
	public String getFd_zdbz(){
	  		return fd_zdbz;
	}
	
	public void setFd_zdbz(String fd_zdbz) {
		if(fd_zdbz==null){
			this.fd_zdbz = "";
		}else{
			this.fd_zdbz = fd_zdbz;
		}
	}		
	
	@Column(name="FD_SJZD",length=255)
	public String getFd_sjzd(){
	  		return fd_sjzd;
	}
	
	public void setFd_sjzd(String fd_sjzd) {
		this.fd_sjzd = fd_sjzd;
	}		
	
	@Column(name="FD_GXR",length=40)
	public String getFd_gxr(){
	  		return fd_gxr;
	}
	
	public void setFd_gxr(String fd_gxr) {
		this.fd_gxr = fd_gxr;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_GXSJ")
	public Date getFd_gxsj(){
	    if(fd_gxsj == null) {
	        return null;
	    }
	    return (Date) fd_gxsj.clone();  
	}
	
	public void setFd_gxsj(Date fd_gxsj) {
	    if(fd_gxsj == null) {
	        this.fd_gxsj = null;
	    }else {
	        this.fd_gxsj = (Date) fd_gxsj.clone();
	    }  
	}		
	
	@Column(name="FD_CJR",length=40)
	public String getFd_cjr(){
	  		return fd_cjr;
	}
	
	public void setFd_cjr(String fd_cjr) {
		this.fd_cjr = fd_cjr;
	}		
	@Temporal(value =TemporalType.TIMESTAMP)
	@Column(name="FD_CJSJ")
	public Date getFd_cjsj(){
	    if(fd_cjsj == null) {
	        return null;
	    }
	    return (Date) fd_cjsj.clone();  
	}
	
	public void setFd_cjsj(Date fd_cjsj) {
	    if(fd_cjsj == null) {
	        this.fd_cjsj = null;
	    }else {
	        this.fd_cjsj = (Date) fd_cjsj.clone();
	    }  
	}		
}

