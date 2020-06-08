package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Customer;
import com.jyc.o2o_demo.bean.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public Customer selectCustomer(String username, String password);


    /**
     * 创建顾客
     * @param customer
     * @return
     */
    public Integer createCustomer(Customer customer);

    /**
     * 创建工作人员
     * @param username
     * @param password
     * @return
     */
    public Worker createWorker(String username, String password);

}
