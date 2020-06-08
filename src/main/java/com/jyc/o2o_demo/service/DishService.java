package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.constant.DishConstants;
import com.jyc.o2o_demo.dao.DishMapper;
import com.jyc.o2o_demo.dto.DishDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishMapper dishMapper;

    public List<DishDTO> getAllDishes() {
        List<Dish> dishes = dishMapper.getAllDishes();
        List<DishDTO> dishDTOS = new ArrayList<>();
        for (int i = 0; i < dishes.size(); i++) {
            dishDTOS.add(toDishDTO(dishes.get(i)));
        }
        return dishDTOS;
    }

    public List<DishDTO> getDishesByType(String type) {
        List<Dish> dishes = dishMapper.getDishesByType(type);
        List<DishDTO> dishDTOS = new ArrayList<>();
        for (int i = 0; i < dishes.size(); i++) {
            dishDTOS.add(toDishDTO(dishes.get(i)));
        }
        return dishDTOS;
    }

    public DishDTO getDishInfo(Integer id){
        Dish dish = dishMapper.getDishInfo(id);
        DishDTO dishDTO = toDishDTO(dish);
        return dishDTO;
    }

    private DishDTO toDishDTO(Dish dish) {
        DishDTO dishDTO = new DishDTO(dish);
        try {
            File file = new File(DishConstants.PIC_PATH + dish.getPic());
            System.out.println(DishConstants.PIC_PATH + dish.getPic());
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,inputStream.available());
            dishDTO.setPic(bytes);
        } catch (Exception e) {
            System.out.println("图片文件打开失败");
        }
        return dishDTO;
    }

}
