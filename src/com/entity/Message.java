package com.entity;

import com.util.FieldMeta;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2016/10/10.
 */
public class Message extends BaseEntity implements Serializable {
    /**
     *
     */
    @FieldMeta(description = "数据唯一性ID", isNull = false, size = 50, isPrimaryKey = true)
    private String id;
    @FieldMeta(description="用户名",size=50)
    private String name;
    @FieldMeta(description = "标题", size = 1000)
    private String title;
    @FieldMeta(description="留言内容",size=4000)
    private String context;
    @FieldMeta(description = "生成时间", size = 100)
    private Date createDate;
    @FieldMeta(description = "IP", size = 100)
    private String ip;
    @FieldMeta(description="邮箱",size=500)
    private String email;
    @FieldMeta(description="电话",size=50)
    private String tel;
    @FieldMeta(description="删除",size=50)
    private Integer delflag=0;

    private String cxtj;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getCxtj() {
        return cxtj;
    }

    public void setCxtj(String cxtj) {
        this.cxtj = cxtj;
    }
}
