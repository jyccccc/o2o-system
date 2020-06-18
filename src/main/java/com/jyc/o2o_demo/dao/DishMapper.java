package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品DAO
 */
@Mapper
@Repository
public interface DishMapper {

    public List<Dish> getAllDishes();

    public List<Dish> getDishesByType(String type);

    public Dish getDishInfo(Integer id);

    Integer updateDishById(Integer dishId, Integer state);

    Integer addDish(Dish dish);
}
