package com.immortal.half.wu.bean.enums;

import com.sun.istack.internal.NotNull;

/**
 * 会员类型
 */
public enum VIP_TYPE {

    VIP_TYPE_ORDINARY(0),
    VIP_TYPE_SENIOR(1),
    VIP_TYPE_SUPER(2);

    VIP_TYPE(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

}
