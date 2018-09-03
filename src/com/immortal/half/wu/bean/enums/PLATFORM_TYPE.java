package com.immortal.half.wu.bean.enums;

/**
 * 对应平台JD 雪球
 */
public enum PLATFORM_TYPE {

    PLATFORM_TYPE_JD(1),
    PLATFORM_TYPE_XQ(0);

    PLATFORM_TYPE(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

}
