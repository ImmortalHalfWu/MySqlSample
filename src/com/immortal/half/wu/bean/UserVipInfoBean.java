package com.immortal.half.wu.bean;

import com.immortal.half.wu.bean.enums.VIP_TYPE;
import com.sun.istack.internal.NotNull;

public class UserVipInfoBean extends BaseBean{

    private final String startTime;
    private final String endTime;
    private final transient VIP_TYPE vipType;

    public UserVipInfoBean(Integer id, Integer userId, String startTime, String endTime, VIP_TYPE vipType) {
        super(id, userId);
        this.startTime = startTime;
        this.endTime = endTime;
        this.vipType = vipType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public VIP_TYPE getVipType() {
        return vipType;
    }
}
