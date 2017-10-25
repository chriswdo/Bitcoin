package com.eshore.datasupport.metadata.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lsl on 2017/5/10.
 * 字典明细表
 */
@Entity
@Table(name="DSJZC_ZDMX")
public class Zdmx implements Serializable{
    private String id;
    private String fd_zdmc; //字典名称
    private String fd_zdbm; //字典编码
    private Date fd_gxsj;   //更新时间
    private String fd_gxr;  //更新人
    private String fd_cjr;  //创建人
    private Date fd_cjsj;   //创建时间
    private String fd_zdms; //字典描述
    private String fd_flbm;//字典分类编码

    public Zdmx(){

    }

    @Id

    @Column(name="ID",length=40)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="FD_ZDMC",length = 255)
    public String getFd_zdmc() {
        return fd_zdmc;
    }

    public void setFd_zdmc(String fd_zdmc) {
        this.fd_zdmc = fd_zdmc;
    }

    @Column(name="FD_ZDBM",length = 255)
    public String getFd_zdbm() {
        return fd_zdbm;
    }

    public void setFd_zdbm(String fd_zdbm) {
        this.fd_zdbm = fd_zdbm;
    }

    @Temporal(value =TemporalType.TIMESTAMP)
    @Column(name="FD_GXSJ")
    public Date getFd_gxsj() {
        return fd_gxsj==null?null:(Date)fd_gxsj.clone();
    }

    public void setFd_gxsj(Date fd_gxsj) {
        this.fd_gxsj = fd_gxsj ==null?null:(Date)fd_gxsj.clone();
    }

    @Column(name="FD_GXR",length = 40)
    public String getFd_gxr() {
        return fd_gxr;
    }

    public void setFd_gxr(String fd_gxr) {
        this.fd_gxr = fd_gxr;
    }

    @Column(name="FD_CJR",length = 40)
    public String getFd_cjr() {
        return fd_cjr;
    }

    public void setFd_cjr(String fd_cjr) {
        this.fd_cjr = fd_cjr;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="FD_CJSJ")
    public Date getFd_cjsj() {
        return fd_cjsj==null?null:(Date)fd_cjsj.clone();
    }

    public void setFd_cjsj(Date fd_cjsj) {
        this.fd_cjsj = fd_cjsj ==null?null:(Date)fd_cjsj.clone();
    }

    @Column(name="FD_ZDMS",length = 1000)
    public String getFd_zdms() {
        return fd_zdms;
    }

    public void setFd_zdms(String fd_zdms) {
        this.fd_zdms = fd_zdms;
    }

    @Column(name="FD_FLBM",length = 40)
    public String getFd_flbm() {
        return fd_flbm;
    }

    public void setFd_flbm(String fd_flbm) {
        this.fd_flbm = fd_flbm;
    }
}
