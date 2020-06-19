package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Customer;
import com.jyc.o2o_demo.bean.User;
import com.jyc.o2o_demo.bean.Worker;
import com.jyc.o2o_demo.dao.UserMapper;
import com.jyc.o2o_demo.utils.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册 + 事务
     * @param username
     * @param password
     * @param phone
     * @return
     */
    @Transactional
    public User doRegister(String username,String password,String phone,Integer authority) {
        User user = new User(username,password,authority,phone);
        if (userMapper.createUser(user) == 1) {
            return user;
        } else {
            return null;
        }
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User doLogin(String username, String password,Integer authority) {
        User user = userMapper.selectUser(username, password,authority);
        System.out.println("账号："+ username + " 密码：" + Base64Util.decode(password) + "尝试登录");
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

}
