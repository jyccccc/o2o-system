package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.bean.Order;
import com.jyc.o2o_demo.constant.OrderConstants;
import com.jyc.o2o_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/orders/submit")
    public Msg submitOrder(Order order) {
        Msg msg = new Msg();
        if (order.getCreateTime() == null) {
            order.setCreateTime(new Date());
        }
        if (order.getState() == null) {
            order.setState(OrderConstants.STATE_UNCONFIRMED);
        }
        Order order1 = orderService.submitOrder(order);
        if(order1 != null) {
            msg.setCM(200,"提交成功");
            msg.putData("Order",order1);
        }
        return msg;
    }

}
