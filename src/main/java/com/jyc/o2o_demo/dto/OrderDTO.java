package com.jyc.o2o_demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jyc.o2o_demo.bean.DishOrder;
import com.jyc.o2o_demo.bean.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 若不提供get、set、constructor，会报序列化错误！
 */
public class OrderDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer customerId;
    private Date createTime;
    private Double price;
    private Integer state;
    private String notes;
    private List<DishOrderDTO> dishList;
    private Integer tableId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<DishOrderDTO> getDishList() {
        return dishList;
    }

    public void setDishList(List<DishOrderDTO> dishList) {
        this.dishList = dishList;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.createTime = order.getCreateTime();
        this.customerId = order.getCustomerId();
        this.price = order.getPrice();
        this.tableId = order.getTableId();
        this.notes = order.getNotes();
        this.state = order.getState();
        this.dishList = new ArrayList<>();
    }

    public void PutData(DishOrderDTO dishOrderDTO) {
        this.dishList.add(dishOrderDTO);
    }
}
