package com.eshore.datasupport.metadata.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lsl on 2017/4/21.
 */
@Entity
@Table(name="DSJZC_ZT")
public class Zt implements Serializable {
    private String id;
    private String fd_ztmc;//主题名称
    private String fd_zt; //状态  N失效 Y有效
    private String fd_ms; //描述
    private Date fd_cjsj;//创建时间
    private String fd_gxr;//数据字典更新人标识
    private Date fd_gxsj;//数据字典更新时间
    private String fd_cjr;//创建人标识

    public Zt() {
    }

    @Id

    @Column(name="ID",length=40)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="FD_ZTMC",length=255)
    public String getFd_ztmc() {
        return fd_ztmc;
    }

    public void setFd_ztmc(String fd_ztmc) {
        this.fd_ztmc = fd_ztmc;
    }

    @Column(name="FD_ZT",length=2)
    public String getFd_zt() {
        return fd_zt;
    }

    public void setFd_zt(String fd_zt) {
        this.fd_zt = fd_zt;
    }

    @Column(name="FD_MS",length=1000)
    public String getFd_ms() {
        return fd_ms;
    }

    public void setFd_ms(String fd_ms) {
        this.fd_ms = fd_ms;
    }

    @Temporal(value =TemporalType.TIMESTAMP)
    @Column(name="FD_CJSJ")
    public Date getFd_cjsj() {
        return fd_cjsj==null?null:(Date)fd_cjsj.clone();
    }

    public void setFd_cjsj(Date fd_cjsj) {
        this.fd_cjsj = fd_cjsj ==null?null:(Date)fd_cjsj.clone();
    }

    @Column(name = "FD_GXR",length = 40)
    public String getFd_gxr() {
        return fd_gxr;
    }

    public void setFd_gxr(String fd_gxr) {
        this.fd_gxr = fd_gxr;
    }

    @Temporal(value =TemporalType.TIMESTAMP)
    @Column(name="FD_GXSJ")
    public Date getFd_gxsj() {
        return fd_gxsj==null?null:(Date)fd_gxsj.clone();
    }

    public void setFd_gxsj(Date fd_gxsj) {
        this.fd_gxsj = fd_gxsj ==null?null:(Date)fd_gxsj.clone();
    }

    @Column(name = "FD_CJR",length = 40)
    public String getFd_cjr() {
        return fd_cjr;
    }

    public void setFd_cjr(String fd_cjr) {
        this.fd_cjr = fd_cjr;
    }
}
