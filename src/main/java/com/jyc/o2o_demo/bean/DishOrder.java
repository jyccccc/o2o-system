package com.jyc.o2o_demo.bean;

import java.awt.*;
import java.util.Date;

/**
 * 选菜表
 */
public class DishOrder {

    private Integer id;
    private Integer orderId;
    private Integer dishId;
    private Date createTime;
    private Integer dishNum;
    private Double price;
    private String name;
    private String pic;

    public DishOrder(Integer orderId, Integer dishId, Date createTime, Integer dishNum, Double price, String name, String pic) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.createTime = createTime;
        this.dishNum = dishNum;
        this.price = price;
        this.name = name;
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDishNum() {
        return dishNum;
    }

    public void setDishNum(Integer dishNum) {
        this.dishNum = dishNum;
    }
}
