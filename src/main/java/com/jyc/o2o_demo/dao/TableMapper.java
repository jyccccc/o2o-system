package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Table;
import javafx.scene.control.Tab;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 餐桌DAO
 */
@Mapper
@Repository
public interface TableMapper {

    public List<Table> getDishes();

    public Table getDishById(Integer id);

}
