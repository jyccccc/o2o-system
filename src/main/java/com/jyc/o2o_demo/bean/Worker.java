package com.jyc.o2o_demo.bean;

import com.jyc.o2o_demo.bean.User;
import com.jyc.o2o_demo.constant.UserConstants;

/**
 * 员工
 */
public class Worker extends User {

    public Worker() {
        this.setAuthority(UserConstants.WORKER_AUTHORITY);
    }

}
