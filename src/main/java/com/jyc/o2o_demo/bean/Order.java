package com.jyc.o2o_demo.bean;

import java.util.Date;
import java.util.List;

/**
 * 订单
 */
public class Order {

    private Integer id;
    private Integer customerId;
    private Date createTime;
    private Double price;
    private Integer state;
    private String notes;
    private List<Dish> dishList;
    private Integer tableId;

    public Order() {
    }

    public Order(Integer customerId, Date createTime, Double price, Integer state) {
        this.customerId = customerId;
        this.createTime = createTime;
        this.price = price;
        this.state = state;
    }

    public Order(Integer customerId, Date createTime, Double price, Integer state, String notes, Integer tableId) {
        this.customerId = customerId;
        this.createTime = createTime;
        this.price = price;
        this.state = state;
        this.notes = notes;
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", state=" + state +
                ", notes='" + notes + '\'' +
                ", dishList=" + dishList +
                ", tableId=" + tableId +
                '}';
    }

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

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}
