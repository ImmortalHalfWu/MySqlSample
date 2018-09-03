package com.immortal.half.wu.bean.enums;

/**
 * 指定交易类型
 */
public enum ORDER_TYPE {

    ORDER_TYPE_BUY(0),
    ORDER_TYPE_SELL(1),
    ORDER_TYPE_ALL(2);

    ORDER_TYPE(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

}
