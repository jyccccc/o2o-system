package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Customer;
import com.jyc.o2o_demo.bean.User;
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
    public User selectUser(String username, String password,Integer authority);


    /**
     * 创建用户
     * @param user
     * @return
     */
    public Integer createUser(User user);


}
