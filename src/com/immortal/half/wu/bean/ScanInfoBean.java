package com.immortal.half.wu.bean;

import com.immortal.half.wu.bean.enums.ORDER_TYPE;

public class ScanInfoBean extends BaseBean{

    private String scanUrl;
    private String tagName;
    private transient ORDER_TYPE orderTypeEnum;
    private Integer frequency;
    private Boolean canUser;
    private Integer orderType;

    public ScanInfoBean(Integer id, Integer userId, String scanUrl, String tagName, ORDER_TYPE orderType, Integer frequency, Boolean canUser) {
        super(id, userId);
        this.scanUrl = scanUrl;
        this.tagName = tagName;
        this.orderTypeEnum = orderType;
        this.frequency = frequency;
        this.canUser = canUser;
        this.orderType = orderType.getCode();
    }

    public String getScanUrl() {
        return scanUrl;
    }

    public String getTagName() {
        return tagName;
    }

    public ORDER_TYPE getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Boolean isCanUser() {
        return canUser;
    }

    public Integer getOrderType() {
        return orderType;
    }
}
