package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.bean.DishOrder;
import com.jyc.o2o_demo.bean.Order;
import com.jyc.o2o_demo.constant.DishConstants;
import com.jyc.o2o_demo.constant.OrderConstants;
import com.jyc.o2o_demo.dao.OrderMapper;
import com.jyc.o2o_demo.dto.DishDTO;
import com.jyc.o2o_demo.dto.DishOrderDTO;
import com.jyc.o2o_demo.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

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
            orderMapper.insertOrder(order);  // order的id为主键
            for (DishOrder dishOrder : order.getDishList()) {
                dishOrder.setOrderId(order.getId());
            }
            orderMapper.insertOrderDishes(order);
            // 通知worker
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param state
     * @return
     */
    @Transactional
    public Integer modifyOrderStateById(Integer orderId, Integer state) {
        try {
            Integer res = orderMapper.modifyOrderStateById(orderId,state);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据用户Id查询订单
     * @param customerId
     * @return
     */
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        try {
            List<Order> orders = orderMapper.selectOrderByCustomerId(customerId);
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为订单加菜
     * @param orderId
     * @param dishes
     * @return
     */
    @Transactional
    public Integer addDishesToOrder(Integer orderId, List<DishOrder> dishes) {
        Order order = new Order(orderId,dishes);
        Double addPrice = 0.0;
        for (DishOrder dishOrder : dishes) {
            addPrice += dishOrder.getPrice();
            dishOrder.setOrderId(orderId);
        }
        Integer res1 = orderMapper.insertOrderDishes(order);  // 插入新菜
        Integer res2 = orderMapper.modifyOrderPrice(orderId,addPrice);  // 修改订单价格
        Integer res3 = orderMapper.modifyOrderStateById(orderId, OrderConstants.STATE_UNCONFIRMED);  // 修改订单状态
        return res1*res2*res3;
    }

    /**
     * 根据用户id和订单id查询订单详情
     * @param customerId
     * @param orderId
     * @return
     */
    public OrderDTO getOrdersByCustomerIdOrderId(Integer customerId,Integer orderId) {
        Order order = orderMapper.getOrdersByCustomerIdOrderId(customerId, orderId);
        OrderDTO orderDTO = toOrderDTO(order);
        return orderDTO;
    }

    private OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(order);
        try {
            for (DishOrder dishOrder : order.getDishList()) {
                File file = new File(DishConstants.PIC_PATH + dishOrder.getPic());
                System.out.println(DishConstants.PIC_PATH + dishOrder.getPic());
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes,0,inputStream.available());
                orderDTO.PutData(new DishOrderDTO(dishOrder,bytes));
            }
        } catch (Exception e) {
            System.out.println("图片文件打开失败");
        }
        return orderDTO;
    }


    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }
}
