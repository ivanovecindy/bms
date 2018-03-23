package com.entity;

import com.util.FieldMeta;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2016/10/10.
 */
public class User extends BaseEntity implements Serializable {
    /**
     *
     */
    @FieldMeta(description = "数据唯一性ID", isNull = false, size = 50, isPrimaryKey = true)
    private String id;
    @FieldMeta(description="用户名",size=50)
    private String name;
    @FieldMeta(description="登录名",size=50)
    private String loginName;
    @FieldMeta(description = "密码", size = 100)
    private String pwd;
    @FieldMeta(description = "注册时间", size = 100)
    private Date regditDate;
    @FieldMeta(description = "最后登录时间", size = 100)
    private Date lastLoginDate;
    @FieldMeta(description = "IP", size = 100)
    private String ip;
    @FieldMeta(description = "用户类型", size = 100)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getRegditDate() {
        return regditDate;
    }

    public void setRegditDate(Date regditDate) {
        this.regditDate = regditDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
