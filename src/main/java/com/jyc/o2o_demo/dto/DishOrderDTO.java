package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.DishOrder;

import java.io.Serializable;

public class DishOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private DishOrder dishOrder;
    private byte[] pic;

    public DishOrderDTO(DishOrder dishOrder) {
        this.dishOrder = dishOrder;
    }

    public DishOrderDTO(DishOrder dishOrder, byte[] pic) {
        this.dishOrder = dishOrder;
        this.pic = pic;
    }

    public DishOrder getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(DishOrder dishOrder) {
        this.dishOrder = dishOrder;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
