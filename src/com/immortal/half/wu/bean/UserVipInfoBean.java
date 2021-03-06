package com.immortal.half.wu.bean;

import com.immortal.half.wu.bean.enums.VIP_TYPE;

public class UserVipInfoBean extends BaseBean{

    private String startTime;
    private String endTime;
    private VIP_TYPE vipTypeEnum;
    private Integer vipType;

    public UserVipInfoBean(Integer id, Integer userId, String startTime, String endTime, Integer vipType) {
        super(id, userId);
        this.startTime = startTime;
        this.endTime = endTime;
        if (vipType != null) {
            this.vipTypeEnum = VIP_TYPE.valueOf(vipType);
            this.vipType = vipTypeEnum.getCode();
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public VIP_TYPE getVipTypeEnum() {
        return vipTypeEnum;
    }

    public Integer getVipType() {
        return vipType;
    }

    public static UserVipInfoBean newInstanceByVipType(Integer id, Integer userId, String startTime, String endTime, VIP_TYPE vipType) {
        return new UserVipInfoBean(id, userId, startTime, endTime, vipType.getCode());
    }

    private static UserVipInfoBean NULL_INSTANCE;
    public static UserVipInfoBean newInstance() {
        if (NULL_INSTANCE == null) {
            synchronized (UserVipInfoBean.class) {
                NULL_INSTANCE = new UserVipInfoBean(null, null, null, null, null);
            }
        }
        return NULL_INSTANCE;
    }
}
