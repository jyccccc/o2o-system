package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.*;
import com.jyc.o2o_demo.constant.OrderConstants;
import com.jyc.o2o_demo.constant.TableConstants;
import com.jyc.o2o_demo.dto.DishDTO;
import com.jyc.o2o_demo.dto.OrderDTO;
import com.jyc.o2o_demo.service.OrderService;
import com.jyc.o2o_demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * @return  订单列表
     */
    @GetMapping("/orders")
    public List<Order> getAllOrders(HttpServletResponse response) throws IOException {
        System.out.println("Get all orders");
        List<Order> orderList = orderService.getAllOrders();
        if (!orderList.isEmpty()) {
            response.setStatus(200);
            response.addIntHeader("orderLens",orderList.size());
        } else {
            response.sendError(403,"Can't find orders");
        }
        return orderList;
    }

    /**
     * 提交订单
     * @param order 提交订单
     * @return  提交好的订单
     */
    @PostMapping("/orders/submit")
    public Order submitOrder(@RequestBody Order order, HttpServletResponse response) throws IOException {
        Msg msg = new Msg();
        Table table = tableService.getTableById(order.getTableId());
        if (table.getState() != TableConstants.TABLE_EMPTY) {  // 餐桌被预定了
            response.sendError(400,"Table has already ordered!");
            return null;
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
            response.setStatus(200);
        } else {
            response.sendError(503,"Fail to submit order!");
        }
        return order1;
    }

    /**
     * 更新订单状态-用户审核通过
     * @param orderId 订单id
     * @param state  订单状态
     * @return  修改结果
     */
    @PutMapping("/orders/{id}")
    public Integer modifyOrderStateById(@PathVariable("id")Integer orderId, @RequestParam("state") Integer state,
                                        HttpServletResponse response) throws IOException {
        System.out.println("OrderId = " + orderId + "modify state to " + state);
        Integer result = orderService.modifyOrderStateById(orderId,state);
        if (result != 0) {
            response.setStatus(200);
        } else {
            response.sendError(507,"Can't modify order's state");
        }
        return result;
    }

    /**
     * 为订单加菜-重置订单状态+金额+菜表
     * @param order 加菜的子订单
     * @return 新的总订单
     */
    @PostMapping("/orders/dishes")
    public OrderDTO addDishesToOrder(@RequestBody Order order, HttpServletResponse response) throws IOException {
        System.out.println("order(id=" + order.getId() + ") add dishes: " + order.getDishList());
        Msg msg = new Msg();
        for (DishOrder dishOrder: order.getDishList()) {
            if (dishOrder.getCreateTime() == null) {
                dishOrder.setCreateTime(new Date());
            }
        }
        Integer res = orderService.addDishesToOrder(order.getId(),order.getDishList());
        if (res != 0) {
            response.setStatus(200);
        } else {
            response.sendError(503,"Add dishes fail");
        }
        return orderService.getOrderById(order.getId());
    }

    /**
     * 根据用户id和订单id查询订单详情
     * @param orderId  订单id
     * @return  订单
     */
    @GetMapping("/orders/id")
    public OrderDTO getOrdersByCustomerIdOrderId(@RequestParam("orderId") Integer orderId,HttpServletResponse response) throws IOException {
        System.out.println("customer" + " search his order(id = " + orderId + ")");
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if (orderDTO != null) {
            response.setStatus(200);
        } else {
            response.sendError(403,"Can't find order");
        }
        return orderDTO;
    }

    /**
     * 根据用户Id查询订单
     * @param customerId  用户id
     * @return  订单列表
     */
    @GetMapping("/orders/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable("customerId") Integer customerId,HttpServletResponse response) throws IOException {
        System.out.println("customer whose id = " + customerId + " search his orders");
        Msg msg = new Msg();
        List<Order> orderList = orderService.getOrdersByCustomerId(customerId);
        if (!orderList.isEmpty()) {
            response.setStatus(200);
            response.addIntHeader("orderLens",orderList.size());
        } else {
            response.sendError(403,"Can't find orders");
        }
        return orderList;
    }

    // @PostMapping("/orders/pay/{orderId}")
}
