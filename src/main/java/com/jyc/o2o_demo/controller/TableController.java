package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.bean.Table;
import com.jyc.o2o_demo.dto.TableDTO;
import com.jyc.o2o_demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public Msg getAllTables() {
        List<TableDTO> tableList = tableService.getAllTables();
        Msg msg = new Msg();
        if (tableList.size() != 0) {
            msg.setCM(200,"查询成功");
            msg.putData("tables",tableList);
            msg.putData("length",tableList.size());
            return msg;
        }
        msg.setCM(400,"查询失败");
        return msg;
    }

}
