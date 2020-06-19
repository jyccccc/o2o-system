package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Dish;
import com.jyc.o2o_demo.constant.DishConstants;
import com.jyc.o2o_demo.dao.DishMapper;
import com.jyc.o2o_demo.dto.DishDTO;
import com.jyc.o2o_demo.utils.PicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DishService {

    @Autowired
    private DishMapper dishMapper;


    /**
     * 获取所有菜品
     * @return
     */
    public List<DishDTO> getAllDishes() {
        List<Dish> dishes = dishMapper.getAllDishes();
        List<DishDTO> dishDTOS = new ArrayList<>();
        for (int i = 0; i < dishes.size(); i++) {
            dishDTOS.add(toDishDTO(dishes.get(i)));
        }
        return dishDTOS;
    }

    /**
     * 获取指定类型的菜品
     * @param type
     * @return
     */
    public List<DishDTO> getDishesByType(String type) {
        List<Dish> dishes = dishMapper.getDishesByType(type);
        List<DishDTO> dishDTOS = new ArrayList<>();
        for (int i = 0; i < dishes.size(); i++) {
            dishDTOS.add(toDishDTO(dishes.get(i)));
        }
        return dishDTOS;
    }

    /**
     * 获取指定ID的菜品
     * @param id
     * @return
     */
    public DishDTO getDishInfo(Integer id){
        Dish dish = dishMapper.getDishInfo(id);
        DishDTO dishDTO = toDishDTO(dish);
        return dishDTO;
    }

    /**
     * 转换为DishDTO
     * @param dish
     * @return
     */
    private DishDTO toDishDTO(Dish dish) {
        DishDTO dishDTO = new DishDTO(dish);
        try {
            byte[] bytesByPath = PicUtil.getBytesByPath(DishConstants.PIC_PATH + dish.getPic());
            dishDTO.setPic(bytesByPath);
        } catch (Exception e) {
            System.out.println("图片文件打开失败");
        }
        return dishDTO;
    }

    /**
     * 增加菜品
     * @param dish
     * @param file
     * @return
     */
    @Transactional
    public DishDTO addDish(Dish dish, MultipartFile file){
        String filename = null;  // 文件名
        byte[] bytes = null;
        try {
            if (!file.isEmpty()) {  // 图片非空，保存图片
                filename = file.getOriginalFilename();  // 获取图片信息
                file.transferTo(Paths.get(DishConstants.PIC_PATH + filename));
                bytes = file.getBytes();
            } else {  // 图片为空，置为默认图片
                filename = DishConstants.PIC_DEFAULT;
                bytes = PicUtil.getBytesByPath(DishConstants.PIC_PATH +filename);
            }
        } catch (AccessDeniedException e) {  // 存储异常，置为默认图片
            filename = DishConstants.PIC_DEFAULT;
            bytes = PicUtil.getBytesByPath(DishConstants.PIC_PATH +filename);
        } catch (Exception e) {  // 读取异常
            e.printStackTrace();
        } finally {
            dish.setPic(filename);
            Integer res = dishMapper.addDish(dish);  // 添加进数据库
            if (res != 0) {  // 添加成功
                DishDTO dishDTO = new DishDTO(dish, bytes);
                return dishDTO;
            }
            return null;
        }
    }

    /**
     * 更新菜品状态
     * @param dishId
     * @param state
     * @return
     */
    @Transactional
    public Integer updateDishById(Integer dishId, Integer state) {
        return dishMapper.updateDishById(dishId,state);
    }
}
