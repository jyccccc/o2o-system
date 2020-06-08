package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.Dish;

import java.io.File;

public class DishDTO {

    private Dish dish;
    private byte[] pic;

    public DishDTO(Dish dish) {
        this.dish = dish;
    }

    public DishDTO() {
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public DishDTO(Dish dish, byte[] pic) {
        this.dish = dish;
        this.pic = pic;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }


}
