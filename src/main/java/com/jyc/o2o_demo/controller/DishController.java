package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.dto.DishDTO;
import com.jyc.o2o_demo.service.DishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 查询所有菜品
     * @return
     */
    @GetMapping("/dishes/all")
    public Msg getAllDishes() {
        Msg msg = new Msg(200, "查询成功");
        System.out.println("查询所有菜品");
        List<DishDTO> dishes = dishService.getAllDishes();
        if(dishes.size() != 0) {
            msg.putData("brief-dishList",dishes);
        } else {
            msg.setCM(200,"查询失败");
        }
        return msg;
    }

    /**
     * 查询指定类型的菜品
     * @param type
     * @return
     */
    @GetMapping("/dishes")
    public Msg getDishesByType(@Param("type") String type) {
        Msg msg = new Msg(200, "查询成功");
        System.out.println("查询类别为" + type + "的菜品");
        List<DishDTO> dishes = dishService.getDishesByType(type);
        if(dishes.size() != 0) {
            msg.putData("brief-dishList",dishes);
        } else {
            msg.setCM(200,"查询失败");
        }
        return msg;
    }

    /**
     * 查询指定菜品的详情
     * @param id
     * @return
     */
    @GetMapping("/dishes/{id}")
    public Msg getDishInfo(@PathVariable("id") Integer id) {
        Msg msg = new Msg(200, "查询成功");
        System.out.println("查询ID为" + id + "的菜品");
        DishDTO dish = dishService.getDishInfo(id);
        if(dish != null) {
            msg.putData("dishInfo",dish);
        } else {
            msg.setCM(200,"查询失败");
        }
        return msg;
    }
}
