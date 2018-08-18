package com.shop.server.model;

import java.math.BigDecimal;
import java.util.Date;

public class WxProduct extends BaseModel<Long>{
    private Long companyId;

    private String title;

    private BigDecimal nowCost;

    private BigDecimal cost;

    private String icon;

    private String description;

    private String type;

    private Integer hot;

    private Byte isDeleted;

    private Byte isBanner;

    private Date gmtCreated;

    private Date gmtModified;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getNowCost() {
        return nowCost;
    }

    public void setNowCost(BigDecimal nowCost) {
        this.nowCost = nowCost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Byte isBanner) {
        this.isBanner = isBanner;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}