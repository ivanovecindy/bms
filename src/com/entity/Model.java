package com.entity;

import com.util.FieldMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模块
 * Created by lenovo on 2016/10/10.
 */
public class Model extends BaseEntity implements Serializable {
    /**
     *
     */
    @FieldMeta(description = "数据唯一性ID", isNull = false, size = 50, isPrimaryKey = true)
    private String id;
    @FieldMeta(description = "父ID", isNull = false, size = 50, isPrimaryKey = true)
    private String pId;
    @FieldMeta(description="模块名称",size=50)
    private String modName;
    @FieldMeta(description = "模块类型", size = 100)
    private String modeType;//0为前台1后台
    @FieldMeta(description = "生成时间", size = 100)
    private Date createDate;
    @FieldMeta(description = "是否显示", size = 100)
    private String isView;//0不显示 1 为显示
    @FieldMeta(description = "IP", size = 100)
    private String ip;
    @FieldMeta(description = "显示顺序")
    private Integer xh=0;
    @FieldMeta(description = "模块地址", size = 1000)
    private String url;
    @FieldMeta(description = "网站页面地址", size = 1000)
    private String weburl;
    @FieldMeta(description = "描述", size = 100000)
    private String remark;
    @FieldMeta(description = "图标", size = 100)
    private String icon;
    private List ids;
    private String parent;
    private String cxtj;
    private List<ModelDeal> list_modelDeal = new ArrayList<ModelDeal>();
    private List<Model> list_model = new ArrayList<Model>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getModeType() {
        return modeType;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsView() {
        return isView;
    }

    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List getIds() {
        return ids;
    }

    public void setIds(List ids) {
        this.ids = ids;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCxtj() {
        return cxtj;
    }

    public void setCxtj(String cxtj) {
        this.cxtj = cxtj;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setList_modelDeal(List<ModelDeal> list_modelDeal) {
        this.list_modelDeal = list_modelDeal;
    }

    public List<ModelDeal> getList_modelDeal() {
        return list_modelDeal;
    }

    public List<Model> getList_model() {
        return list_model;
    }

    public void setList_model(List<Model> list_model) {
        this.list_model = list_model;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }
}
