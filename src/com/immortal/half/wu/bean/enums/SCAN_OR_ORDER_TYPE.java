package com.immortal.half.wu.bean.enums;

/**
 * 是扫描还是跟单
 */
public enum SCAN_OR_ORDER_TYPE {

    SCAN_OR_ORDER_TYPE_SCAN(0),
    SCAN_OR_ORDER_TYPE_ORDER(1);

    SCAN_OR_ORDER_TYPE(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

}
