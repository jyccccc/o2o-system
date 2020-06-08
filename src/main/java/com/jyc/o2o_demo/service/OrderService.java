package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Order;
import com.jyc.o2o_demo.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 提交订单
     * @param order
     * @return
     */
    @Transactional
    public Order submitOrder(Order order) {
        try {
            Integer orderId = orderMapper.insertOrder(order);
            order.setId(orderId);
            orderMapper.insertOrderDishes(order);
            // 通知worker
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
