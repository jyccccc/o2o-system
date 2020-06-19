package com.jyc.o2o_demo.bean;

/**
 * 用户基类
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer authority; // 权限
    private String phone;

    public User(String username, String password, Integer authority, String phone) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.phone = phone;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}
