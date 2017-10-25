package com.eshore.datasupport.common.vo;


public class YsjzdVo {
	private String id;//id
	private String zdmc;//元数据字段的字段名称
	private String zdlx;//元数据字段的字段类型
	private Short zdcd;//元数据字段的字段长度
	private Short zdjd;//元数据字段的字段长度
	private String sfzj;//元数据字段的是否主键   Y是; N否
	private String qsz;//元数据字段的缺省值
	private String sfkwk;//元数据字段的是否可为空   Y是;N否
	private String zdbz;//源数据字段的字段描述
	private String fd_sjzd;//元数据字段的数据字典   配置用以对源表数据检查
	private String fd_ysjb_id;
	private Short zdsx;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public Short getZdcd() {
		return zdcd;
	}
	public void setZdcd(Short zdcd) {
		this.zdcd = zdcd;
	}
	public Short getZdjd() {
		return zdjd;
	}
	public void setZdjd(Short zdjd) {
		this.zdjd = zdjd;
	}
	public String getSfzj() {
		return sfzj;
	}
	public void setSfzj(String sfzj) {
		if(sfzj==null){
			this.sfzj="";
		}else{
			this.sfzj = sfzj;
		}
	}
	public String getQsz() {
		return qsz;
	}
	public void setQsz(String qsz) {
		if(qsz==null){
			this.qsz="";
		}else{
			this.qsz = qsz;
		}
	}
	public String getSfkwk() {
		return sfkwk;
	}
	public void setSfkwk(String sfkwk) {
		if(sfkwk.indexOf("Y")!=-1){							
			this.sfkwk ="Y";
		}else{
			this.sfkwk ="N";
		}
	}
	public String getZdbz() {
		return zdbz;
	}
	public void setZdbz(String zdbz) {
		if(zdbz==null){
			this.zdbz="";
		}else{
			this.zdbz = zdbz;
		}
	}
	public String getFd_sjzd() {
		return fd_sjzd;
	}
	public void setFd_sjzd(String fd_sjzd) {
		this.fd_sjzd = fd_sjzd;
	}
	public String getFd_ysjb_id() {
		return fd_ysjb_id;
	}
	public void setFd_ysjb_id(String fd_ysjb_id) {
		this.fd_ysjb_id = fd_ysjb_id;
	}
	public Short getZdsx() {
		return zdsx;
	}
	public void setZdsx(Short zdsx) {
		this.zdsx = zdsx;
	}

	
	
	
	
}
