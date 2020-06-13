package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.Table;

public class TableDTO {

    private Table table;
    private byte[] qrCode;

    public TableDTO() {
    }

    public TableDTO(Table table, byte[] qrCode) {
        this.table = table;
        this.qrCode = qrCode;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }
}
