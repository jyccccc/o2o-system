package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.DishOrder;

import java.io.Serializable;
import java.util.Date;

public class DishOrderDTO  extends DishOrder{
    private static final long serialVersionUID = 1L;
    private byte[] file;


    public DishOrderDTO(Integer orderId, Integer dishId, Date createTime, Integer dishNum, Double price, String name, String pic) {
        super(orderId, dishId, createTime, dishNum, price, name, pic);
    }

    public DishOrderDTO(DishOrder dishOrder) {
        super(dishOrder.getOrderId(),dishOrder.getDishId(),dishOrder.getCreateTime(),
                dishOrder.getDishNum(),dishOrder.getPrice(),dishOrder.getName(),dishOrder.getPic());
        setId(dishOrder.getId());
    }

    public DishOrderDTO(DishOrder dishOrder, byte[] pic) {
        super(dishOrder.getOrderId(),dishOrder.getDishId(),dishOrder.getCreateTime(),
                dishOrder.getDishNum(),dishOrder.getPrice(),dishOrder.getName(),dishOrder.getPic());
        setId(dishOrder.getId());
        this.file = pic;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
