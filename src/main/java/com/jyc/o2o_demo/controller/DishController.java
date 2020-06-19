package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.constant.DishConstants;
import com.jyc.o2o_demo.dto.DishDTO;
import com.jyc.o2o_demo.service.DishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/dishes/tests")
    public List<DishDTO> getAllDishess(HttpServletResponse response) throws IOException {
        System.out.println("查询所有菜品");
        List<DishDTO> dishes = dishService.getAllDishes();
        // dishes.clear();
        if(dishes.size() != 0) {
            response.setStatus(200);
            response.addIntHeader("dishLength",dishes.size());
        } else {
            response.sendError(404,"Not found!!!");
        }
        return dishes;
    }

    /**
     * 查询指定类型的菜品
     * @param type
     * @return
     */
    @GetMapping("/dishes")
    public Msg getDishesByType(@RequestParam("type") String type) {
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

    /**
     * 增加菜品
     * @param name
     * @param file
     * @param type
     * @param price
     * @param description
     * @param state
     * @return
     */
    @PostMapping("/dishes")
    public Msg addDishes(@RequestParam("name")String name, @RequestParam("pic") MultipartFile file,
                         @RequestParam("type")String type, @RequestParam("price")Double price,
                         @RequestParam("description")String description,@RequestParam("state")Integer state) {
        Dish dish = new Dish(name, type, price, description, state);
        if (dish.getState() == null) {
            dish.setState(DishConstants.DISH_STATE_ON);
        }
        DishDTO dishDTO = dishService.addDish(dish,file);
        if (dishDTO != null) {
            Msg msg = new Msg(200, "增加成功");
            msg.putData("dish",dishDTO);
            return msg;
        } else {
            return new Msg(400,"增加失败");
        }
    }

    /**
     * 修改菜品状态，设置菜品上架或下架
     * @param dishState
     * @param dishId
     * @return
     */
    @PutMapping("/dishes/{dishId}")
    public Msg updateDishById(@RequestParam("dishState") Integer dishState, @PathVariable("dishId") Integer dishId) {
        Integer res = dishService.updateDishById(dishId, dishState);
        if(res != 0) {
            return new Msg(200,"修改成功");
        } else {
            return new Msg(400,"修改失败");
        }
    }


}
