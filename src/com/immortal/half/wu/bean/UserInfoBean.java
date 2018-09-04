package com.immortal.half.wu.bean;

import com.sun.istack.internal.Nullable;

public class UserInfoBean extends BaseBean{

    private Integer vipId;
    private String phone;
    private String registTime;
    private Boolean isLogin;
    private String passWord;

    public UserInfoBean(Integer id, Integer userId, Integer vipId, String phone, String registTime, Boolean isLogin, String passWord) {
        super(id, userId);
        this.vipId = vipId;
        this.phone = phone;
        this.registTime = registTime;
        this.isLogin = isLogin;
        this.passWord = passWord;
    }

    public Integer getVipId() {
        return vipId;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegistTime() {
        return registTime;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "vipId=" + vipId +
                ", phone='" + phone + '\'' +
                ", registTime='" + registTime + '\'' +
                ", isLogin=" + isLogin +
                ", passWord='" + passWord + '\'' +
                '}';
    }


    private static UserInfoBean NULL_INSTANCE;
    public static UserInfoBean newInstance() {
        if (NULL_INSTANCE == null) {
            synchronized (UserInfoBean.class) {
                NULL_INSTANCE = new UserInfoBean(null, null, null, null, null, null, null);
            }
        }
        return NULL_INSTANCE;
    }

    public static UserInfoBean newInstanceById(int id) {
        return new UserInfoBean(id, null, null, null, null, null, null);
    }

    public static UserInfoBean newInstanceByVipId(int vipId) {
        return new UserInfoBean(null, null, vipId, null, null, null, null);
    }
}
