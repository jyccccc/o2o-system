package com.jyc.o2o_demo.bean;

/**
 * 餐桌
 */
public class Table {

    private Integer id;
    private String place;
    private Integer state;
    private Integer type;
    private String qrCode;  // 二维码地址

    public Table() {
    }

    public Table(String place, Integer state, Integer type) {
        this.place = place;
        this.state = state;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
