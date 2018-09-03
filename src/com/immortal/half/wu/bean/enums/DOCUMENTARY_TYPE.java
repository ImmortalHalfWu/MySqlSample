package com.immortal.half.wu.bean.enums;

/**
 * 闪电下单 or 严格下单
 */
public enum DOCUMENTARY_TYPE {

    DOCUMENTARY_TYPE_FAST(1),
    DOCUMENTARY_TYPE_STRICT(0);

    DOCUMENTARY_TYPE(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }
}
