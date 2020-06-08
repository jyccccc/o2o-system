package com.jyc.o2o_demo.bean;

import com.jyc.o2o_demo.constant.UserConstants;

/**
 * 管理员
 */
public class Admin extends User {

    public Admin() {
        this.setAuthority(UserConstants.ADMIN_AUTHORITY);
    }

}
