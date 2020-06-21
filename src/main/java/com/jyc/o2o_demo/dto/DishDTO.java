package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.Dish;

import java.io.File;

public class DishDTO extends Dish{

    private byte[] file;

    public DishDTO(Dish dish,byte[] file) {
        super(dish.getName(), dish.getType(), dish.getPrice(), dish.getDescription(), dish.getState());
        setId(dish.getId());
        this.file = file;
    }

    public DishDTO(Dish dish) {
        super(dish.getName(), dish.getType(), dish.getPrice(), dish.getDescription(), dish.getState());
        setId(dish.getId());
    }

    public DishDTO() {
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
