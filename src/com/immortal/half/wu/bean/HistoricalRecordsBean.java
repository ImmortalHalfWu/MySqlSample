package com.immortal.half.wu.bean;


import com.immortal.half.wu.bean.enums.ORDER_TYPE;
import com.immortal.half.wu.bean.enums.PLATFORM_TYPE;
import com.immortal.half.wu.bean.enums.SCAN_OR_ORDER_TYPE;

public class HistoricalRecordsBean extends BaseBean {

    private transient PLATFORM_TYPE platformType;
    private String tagName;
    private transient ORDER_TYPE orderTypeEnum;
    private Integer orderType;
    private String orderTime;
    private String stockNum;
    private String stockMoney;
    private String postitionRatio;
    private Boolean isSuc;
    private String erroMsg;
    private transient SCAN_OR_ORDER_TYPE scanOrOrderTypeEnum;
    private Integer scanOrOrder;


    public HistoricalRecordsBean(
            Integer id,
            Integer userId,
            PLATFORM_TYPE platformType,
            String tagName,
            ORDER_TYPE orderType,
            String orderTime,
            String stockNum,
            String stockMoney,
            String postitionRatio,
            Boolean isSuc,
            String erroMsg,
            SCAN_OR_ORDER_TYPE scanOrOrderType) {
        super(id, userId);
        this.platformType = platformType;
        this.tagName = tagName;
        this.orderTypeEnum = orderType;
        this.orderTime = orderTime;
        this.stockNum = stockNum;
        this.stockMoney = stockMoney;
        this.postitionRatio = postitionRatio;
        this.isSuc = isSuc;
        this.erroMsg = erroMsg;
        this.scanOrOrderTypeEnum = scanOrOrderType;
        this.orderType = orderType.getCode();
        this.scanOrOrder = scanOrOrderType.getCode();
    }

    public PLATFORM_TYPE getPlatformType() {
        return platformType;
    }

    public String getTagName() {
        return tagName;
    }

    public ORDER_TYPE getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getStockNum() {
        return stockNum;
    }

    public String getStockMoney() {
        return stockMoney;
    }

    public String getPostitionRatio() {
        return postitionRatio;
    }

    public Boolean getSuc() {
        return isSuc;
    }

    public String getErroMsg() {
        return erroMsg;
    }

    public SCAN_OR_ORDER_TYPE getScanOrOrderTypeEnum() {
        return scanOrOrderTypeEnum;
    }

    public Integer getScanOrOrder() {
        return scanOrOrder;
    }
}
