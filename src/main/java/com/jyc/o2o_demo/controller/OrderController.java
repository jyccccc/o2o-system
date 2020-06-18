package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.*;
import com.jyc.o2o_demo.constant.OrderConstants;
import com.jyc.o2o_demo.constant.TableConstants;
import com.jyc.o2o_demo.dto.OrderDTO;
import com.jyc.o2o_demo.service.OrderService;
import com.jyc.o2o_demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TableService tableService;

    /**
     * 获取所有订单
     * @return
     */
    @GetMapping("/orders")
    public Msg getAllOrders() {
        System.out.println("Get all orders");
        Msg msg = new Msg();
        List<Order> orderList = orderService.getAllOrders();
        msg.setCM(200,"查询成功");
        msg.putData("orders",orderList);
        msg.putData("length",orderList.size());
        return msg;
    }

    /**
     * 提交订单
     * @param order
     * @return
     */
    @PostMapping("/orders/submit")
    public Msg submitOrder(@RequestBody Order order) {
        Msg msg = new Msg();
        Table table = tableService.getTableById(order.getTableId());
        if (table.getState() != TableConstants.TABLE_EMPTY) {
            msg.setCM(301,"该餐桌无法预约");
            return msg;
        }
        System.out.println("submit order: " + order + "dishes are: " + order.getDishList());
        if (order.getCreateTime() == null) {
            order.setCreateTime(new Date());
        }
        if (order.getState() == null) {
            order.setState(OrderConstants.STATE_UNCONFIRMED);
        }
        for (DishOrder dishOrder: order.getDishList()) {
            if (dishOrder.getCreateTime() == null) {
                dishOrder.setCreateTime(new Date());
            }
        }
        Order order1 = orderService.submitOrder(order);
        // 提交后，2小时后自动置餐桌为空闲状态，用餐结束
        new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        tableService.modifyTableState(order.getTableId(),TableConstants.TABLE_EMPTY);
                    }
                },1000*TableConstants.DINNER_TIME);
            }
        }).start();
        if(order1 != null) {
            msg.setCM(200,"提交成功");
            msg.putData("Order",order1);
        }
        return msg;
    }

    /**
     * 更新订单状态-用户审核通过
     * @param orderId
     * @param state
     * @return
     */
    @PutMapping("/orders/{id}")
    public Msg modifyOrderStateById(@PathVariable("id")Integer orderId,Integer state) {
        System.out.println("OrderId = " + orderId + "modify state to " + state);
        Msg msg = new Msg();
        Integer result = orderService.modifyOrderStateById(orderId,state);
        if (result != 0) {
            msg.setCM(200,"修改成功");
        }
        return msg;
    }

    /**
     * 根据用户Id查询订单
     * @param customerId
     * @return
     */
    @GetMapping("/orders/{customerId}")
    public Msg getOrdersByCustomerId(@PathVariable("customerId") Integer customerId) {
        System.out.println("customer whose id = " + customerId + " search his orders");
        Msg msg = new Msg();
        List<Order> orderList = orderService.getOrdersByCustomerId(customerId);
        msg.setCM(200,"查询成功");
        msg.putData("orders",orderList);
        msg.putData("length",orderList.size());
        return msg;
    }

    /**
     * 为订单加菜-重置订单状态+金额+菜表
     * @param order
     * @return
     */
    @PostMapping("/orders/dishes")
    public Msg addDishesToOrder(@RequestBody Order order) {
        System.out.println("order(id=" + order.getId() + ") add dishes: " + order.getDishList());
        Msg msg = new Msg();
        for (DishOrder dishOrder: order.getDishList()) {
            if (dishOrder.getCreateTime() == null) {
                dishOrder.setCreateTime(new Date());
            }
        }
        Integer res = orderService.addDishesToOrder(order.getId(),order.getDishList());
        if (res != 0) {
            msg.setCM(200,"加菜成功");
            msg.putData("newOrder",orderService.getOrderById(order.getId()));
        } else {
            msg.setCM(400,"加菜失败");
        }
        return msg;
    }

    /**
     * 根据用户id和订单id查询订单详情
     * @param customerId
     * @param orderId
     * @return
     */
    @GetMapping("/orders/{customerId}/{orderId}")
    @ResponseBody
    public Msg getOrdersByCustomerIdOrderId(@PathVariable("customerId") Integer customerId,
                                            @PathVariable("orderId") Integer orderId) {
        System.out.println("customer(id = " + customerId + ") search his order(id = " + orderId + ")");
        Msg msg = new Msg();
        OrderDTO orderDTO = orderService.getOrdersByCustomerIdOrderId(customerId,orderId);
        msg.setCM(200,"查询成功");
        msg.putData("order",orderDTO);
        return msg;
    }

    // @PostMapping("/orders/pay/{orderId}")
}
