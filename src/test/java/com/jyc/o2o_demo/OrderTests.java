package com.jyc.o2o_demo;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.bean.Order;
import com.jyc.o2o_demo.dao.DishMapper;
import com.jyc.o2o_demo.dao.OrderMapper;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderTests {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    DishMapper dishMapper;

    /**
     * 订单查询
     */
    @Test
    public void selectOrderTest() {
        List<Order> orders = orderMapper.selectOrderByCustomerId(1);
        for (Order order: orders) {
            System.out.println(order.toString());
            order.getDishList().forEach((dish) -> {
                System.out.println(dish.toString());
            });
        }
    }

//    /**
//     * 订单插入
//     */
//    @Test
//    public void insertOrderTest() {
//        Order order = new Order(1, DateUtil.now(),55.88,1);
//        List<Dish> dishes = dishMapper.getDishesByType("热门");
//        order.setDishList(dishes);
//        System.out.println(orderMapper.insertOrder(order) + " " + order.getId());
//        orderMapper.insertOrderDishes(order);
//    }
}
