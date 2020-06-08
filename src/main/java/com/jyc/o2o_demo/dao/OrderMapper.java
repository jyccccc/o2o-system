package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单DAO
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * 插入订单
     * @param order
     * @return 插入的orderID
     */
    public Integer insertOrder(Order order);

    /**
     * 插入选菜信息
     * @param order
     * @return
     */
    public Integer insertOrderDishes(Order order);

    /**
     * 根据用户ID查询订单
     * @param customerId
     * @return
     */
    public List<Order> selectOrderByCustomerId(Integer customerId);
}
