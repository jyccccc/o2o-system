package com.jyc.o2o_demo.bean;

import com.jyc.o2o_demo.constant.UserConstants;

/**
 * 消费者
 */
public class Customer extends User {
    private String phone;

    public Customer(String username,String password,String phone) {
        this.phone = phone;
        this.setUsername(username);
        this.setPassword(password);
        this.setAuthority(UserConstants.USER_AUTHORITY);
    }

    public Customer() {
        this.setAuthority(UserConstants.USER_AUTHORITY);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
