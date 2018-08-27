package com.immortal.half.wu.bean;

public class RegisterUserBean {

    private String phone;
    private String password;
    private int userId;

    public RegisterUserBean(String phone, String password, int userId) {
        this.phone = phone;
        this.password = password;
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }
}
