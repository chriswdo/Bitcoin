package com.eshore.datasupport.metadata.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lsl on 2017/5/9.
 * 字典分类
 */
@Entity
@Table(name="DSJZC_ZDFL")
public class Zdfl implements Serializable {
    private String id;
    private String fd_flmc;//字典分类名称
    private String fd_flbm;//字典分类编码
    private Date fd_gxsj;//更新时间
    private String fd_gxr;//更新人
    private String fd_cjr;//创建人
    private Date fd_cjsj;//创建时间
    private String fd_zt;//状态  N失效 Y有效
    public Zdfl(){

    }

    @Id

    @Column(name="ID",length=40)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="FD_FLMC",length=255)
    public String getFd_flmc() {
        return fd_flmc;
    }

    public void setFd_flmc(String fd_flmc) {
        this.fd_flmc = fd_flmc;
    }

    @Column(name="FD_FLBM",length=255)
    public String getFd_flbm() {
        return fd_flbm;
    }

    public void setFd_flbm(String fd_flbm) {
        this.fd_flbm = fd_flbm;
    }

    @Temporal(value =TemporalType.TIMESTAMP)
    @Column(name="FD_GXSJ")
    public Date getFd_gxsj() {
        return fd_gxsj==null?null:(Date)fd_gxsj.clone();
    }

    public void setFd_gxsj(Date fd_gxsj) {
        this.fd_gxsj = fd_gxsj ==null?null:(Date)fd_gxsj.clone();
    }

    @Column(name="FD_GXR",length=40)
    public String getFd_gxr() {
        return fd_gxr;
    }

    public void setFd_gxr(String fd_gxr) {
        this.fd_gxr = fd_gxr;
    }

    @Column(name="FD_CJR",length=40)
    public String getFd_cjr() {
        return fd_cjr;
    }

    public void setFd_cjr(String fd_cjr) {
        this.fd_cjr = fd_cjr;
    }

    @Temporal(value =TemporalType.TIMESTAMP)
    @Column(name="FD_CJSJ")
    public Date getFd_cjsj() {
        return fd_cjsj==null?null:(Date)fd_cjsj.clone();
    }

    public void setFd_cjsj(Date fd_cjsj) {
        this.fd_cjsj = fd_cjsj ==null?null:(Date)fd_cjsj.clone();
    }

    @Column(name="FD_ZT",length=2)
    public String getFd_zt() {
        return fd_zt;
    }

    public void setFd_zt(String fd_zt) {
        this.fd_zt = fd_zt;
    }
}
