package com.jyc.o2o_demo.dao;

import com.jyc.o2o_demo.bean.Table;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 餐桌DAO
 */
@Mapper
@Repository
public interface TableMapper {

    List<Table> getAllTables();

    Integer modifyTableState(Integer id, Integer state);

    Table getTableById(Integer id);

    Integer addTable(Table table);
}
