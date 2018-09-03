package com.immortal.half.wu.bean;

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

    public Boolean isLogin() {
        return isLogin;
    }

    public String getPassWord() {
        return passWord;
    }
}
