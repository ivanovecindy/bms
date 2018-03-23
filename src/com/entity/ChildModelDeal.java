package com.entity;

import com.util.FieldMeta;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块详细(产品列表)附件
 * Created by lenovo on 2016/10/10.
 */
public class ChildModelDeal extends BaseEntity implements Serializable {
    /**
     *
     */
    @FieldMeta(description = "数据唯一性ID", isNull = false, size = 50, isPrimaryKey = true)
    private String id;
    @FieldMeta(description = "模块ID", size = 100)
    private String modeDealId ;
    @FieldMeta(description = "标题", size = 100)
    private String title ;
    @FieldMeta(description = "生成时间", size = 100)
    private Date createDate;
    @FieldMeta(description = "附件名称", size = 1000)
    private String fjmc ;
    @FieldMeta(description = "附件地址", size = 1000)
    private String fjdz;
    @FieldMeta(description = "描述")
    private String  ms;
    @FieldMeta(description = "模块地址", size = 1000)
    private String url;
    @FieldMeta(description = "网站页面地址", size = 1000)
    private String weburl;
    @FieldMeta(description = "是否显示", size = 100)
    private String isView ;//0不显示 1 为显示
    @FieldMeta(description = "显示顺序")
    private Integer xh=0;
    private String cxtj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    public String getFjdz() {
        return fjdz;
    }

    public void setFjdz(String fjdz) {
        this.fjdz = fjdz;
    }

    public String getModeDealId() {
        return modeDealId;
    }

    public void setModeDealId(String modeDealId) {
        this.modeDealId = modeDealId;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCxtj() {
        return cxtj;
    }

    public void setCxtj(String cxtj) {
        this.cxtj = cxtj;
    }

    public String getIsView() {
        return isView;
    }

    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
