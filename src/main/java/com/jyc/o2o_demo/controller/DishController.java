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
     * @return  不完整的菜品列表
     */
    @GetMapping("/dishes/all")
    public List<DishDTO> getAllDishes(HttpServletResponse response) throws IOException {
        System.out.println("查询所有菜品");
        List<DishDTO> dishes = dishService.getAllDishes();
        if(dishes.size() != 0) {
            response.setStatus(200);
            response.setIntHeader("dishLens",dishes.size());
        } else {
            response.sendError(403,"Can't found any dishes");
        }
        return dishes;
    }

    /**
     * 查询指定类型的菜品
     * @param type 菜品类型
     * @return  不完整的菜品列表
     */
    @GetMapping("/dishes")
    public List<DishDTO> getDishesByType(@RequestParam("type") String type,HttpServletResponse response) throws IOException {
        System.out.println("查询类别为" + type + "的菜品");
        List<DishDTO> dishes = dishService.getDishesByType(type);
        if(dishes.size() != 0) {
            response.setStatus(200);
            response.setIntHeader("dishLens",dishes.size());
        } else {
            response.sendError(403,"Can't found any dishes");
        }
        response.addHeader("Access-Control-Allow-Origin","*");  // 跨域
        return dishes;
    }

    /**
     * 查询指定菜品的详情
     * @param id 菜品id
     * @return  菜品详情
     */
    @GetMapping("/dishes/{id}")
    public DishDTO getDishInfo(@PathVariable("id") Integer id,HttpServletResponse response) throws IOException {
        Msg msg = new Msg(200, "查询成功");
        System.out.println("查询ID为" + id + "的菜品");
        DishDTO dish = dishService.getDishInfo(id);
        if(dish != null) {
            response.setStatus(200);
        } else {
            response.sendError(403,"Can't found any dishes");
        }
        return dish;
    }

    /**
     * 增加菜品
     * @param name  菜品名字
     * @param file  菜品图片
     * @param type  菜品类型
     * @param price  菜品价格
     * @param description  菜品描述
     * @param state  菜品状态
     * @return  增加的菜品
     */
    @PostMapping("/dishes")
    public DishDTO addDishes(@RequestParam("name")String name, @RequestParam("pic") MultipartFile file,
                         @RequestParam("type")String type, @RequestParam("price")Double price,
                         @RequestParam("description")String description,@RequestParam("state")Integer state,
                             HttpServletResponse response) throws IOException {
        Dish dish = new Dish(name, type, price, description, state);
        if (dish.getState() == null) {
            dish.setState(DishConstants.DISH_STATE_ON);
        }
        DishDTO dishDTO = dishService.addDish(dish,file);
        if (dishDTO != null) {
            response.setStatus(200);
        } else {
            response.sendError(501,"Can't add new dish");
        }
        return dishDTO;
    }

    /**
     * 修改菜品状态，设置菜品上架或下架
     * @param dishState  改变的状态
     * @param dishId  需要改变的菜品ID
     * @return  修改是否成功
     */
    @PutMapping("/dishes/{dishId}")
    public Integer updateDishById(@RequestParam("dishState") Integer dishState, @PathVariable("dishId") Integer dishId,
                              HttpServletResponse response) throws IOException {
        Integer res = dishService.updateDishById(dishId, dishState);
        if(res != 0) {
            response.setStatus(200);
        } else {
            response.sendError(507,"Can't modify dish's state");
        }
        return res;
    }
}
