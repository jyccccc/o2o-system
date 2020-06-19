package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.bean.Table;
import com.jyc.o2o_demo.dto.TableDTO;
import com.jyc.o2o_demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * 查询所有餐桌
     * @return  餐桌列表
     */
    @GetMapping("/tables")
    public List<TableDTO> getAllTables(HttpServletResponse response) throws IOException {
        List<TableDTO> tableList = tableService.getAllTables();
        if (tableList.size() != 0) {
            response.setStatus(200);
        } else {
            response.sendError(403,"Can't find tables");
        }
        return tableList;
    }

    /**
     * 修改餐桌状态
     * @param tableId  餐桌id
     * @param state  餐桌状态
     * @return  修改后的餐桌
     */
    @PutMapping("/tables/{tableId}")
    public Table updateOrderState(@PathVariable("tableId") Integer tableId,@RequestParam("state") Integer state,
                                  HttpServletResponse response) throws IOException {
        System.out.println("Update table's state to " + state);
        Integer res = tableService.modifyTableState(tableId, state);
        Table table = tableService.getTableById(tableId);
        if (res != 0) {
            response.setStatus(200);
        } else {
            response.sendError(507,"Can't modify table's state");
        }
        return table;
    }

    /**
     * 增加餐桌，二维码后台生成
     * @param state  餐桌状态
     * @param place  餐桌位置
     * @param session
     * @return  增加后的餐桌
     */
    @PostMapping("/tables")
    public Table addTable(@RequestParam("state") Integer state, @RequestParam("place") String place,
                        @RequestParam("type")Integer type, HttpSession session,HttpServletResponse response) throws IOException {
        System.out.println(session.getAttribute("ADMIN_SESSION") + "add table");
        Table table = tableService.addTable(state,place,type);
        if (table != null) {
            response.setStatus(200);
        } else {
            response.sendError(503,"can't add tables");
        }
        return table;
    }

}
