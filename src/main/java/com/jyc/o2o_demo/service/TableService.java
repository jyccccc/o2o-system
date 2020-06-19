package com.jyc.o2o_demo.service;

import com.jyc.o2o_demo.bean.Table;
import com.jyc.o2o_demo.constant.DishConstants;
import com.jyc.o2o_demo.constant.TableConstants;
import com.jyc.o2o_demo.dao.TableMapper;
import com.jyc.o2o_demo.dto.TableDTO;
import com.jyc.o2o_demo.utils.PicUtil;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.PushBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableMapper tableMapper;

    /**
     * 修改餐桌状态
     * @param id
     * @param state
     * @return
     */
    @Transactional
    public Integer modifyTableState(Integer id,Integer state) {
        return tableMapper.modifyTableState(id,state);
    }

    /**
     * 根据餐桌id获取餐桌
     * @param id
     * @return
     */
    public Table getTableById(Integer id) {
        return tableMapper.getTableById(id);
    }

    /**
     * 获取所有餐桌详情
     * @return
     */
    public List<TableDTO> getAllTables() {
        List<Table> tables = tableMapper.getAllTables();
        List<TableDTO> tableDTOS = new ArrayList<>();
        for (Table table :
                tables) {
            tableDTOS.add(toTableDTO(table));
        }
        return tableDTOS;
    }

    /**
     * 增加餐桌
     * @param state
     * @param place
     * @param type
     * @return
     */
    @Transactional
    public Table addTable(Integer state, String place, Integer type) {
        Table table = new Table(place, state, type);
        PicUtil.generateQR(place,TableConstants.PIC_PATH + place +  TableConstants.PIC_FORMAT);  // 生成二维码并保存
        table.setQrCode(place);
        Integer res = tableMapper.addTable(table);
        if (res != 0) {
            return table;
        } else {
            return null;
        }
    }

    private TableDTO toTableDTO(Table table) {
        try {
            byte[] bytes1 = PicUtil.getBytesByPath(TableConstants.PIC_PATH + table.getQrCode() + TableConstants.PIC_FORMAT);
            return new TableDTO(table,bytes1);
        } catch (Exception e) {
            System.out.println("图片文件打开失败");
        }
        return new TableDTO();
    }
}
