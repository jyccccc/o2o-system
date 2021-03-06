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

    /**
     * 修改订单状态
     * @param id
     * @param state
     * @return
     */
    public Integer modifyOrderStateById(Integer id,Integer state);

    /**
     * 修改订单金额
     * @param id
     * @param price
     * @return
     */
    public Integer modifyOrderPrice(Integer id,Double price);

    /**
     * 查询指定用户id和订单id的订单
     * @param cId
     * @param oId
     * @return
     */
    public Order getOrdersByCustomerIdOrderId(Integer cId,Integer oId);

    /**
     * 根据订单号查询订单
     * @param id
     * @return
     */
    public Order getOrderById(Integer id);

    /**
     * 返回所有订单
     * @return
     */
    List<Order> getAllOrders();
}
