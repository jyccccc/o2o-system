package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Customer;
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
     * 消费者注册 + 事务
     * @param username
     * @param password
     * @param phone
     * @return
     */
    @Transactional
    public Customer doRegister(String username,String password,String phone) {
        Customer customer = new Customer(username,password,phone);
        if (userMapper.createCustomer(customer) == 1) {
            return customer;
        } else {
            return null;
        }
    }

    /**
     * 员工加入
     * @param username
     * @param password
     * @return
     */
    public Worker doRegister(String username, String password) {
        Worker worker = userMapper.createWorker(username,password);
        System.out.println("员工---账号："+ username + " 密码：" + Base64Util.decode(password) + "加入");
        if (worker == null) {
            return null;
        } else {
            return worker;
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public Customer doLogin(String username, String password) {
        Customer customer = userMapper.selectCustomer(username, password);
        System.out.println("账号："+ username + " 密码：" + Base64Util.decode(password) + "尝试登录");
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }

}
