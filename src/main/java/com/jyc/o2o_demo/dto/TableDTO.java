package com.jyc.o2o_demo.dto;

import com.jyc.o2o_demo.bean.Table;

public class TableDTO extends Table{

    private byte[] file;

    public TableDTO() {
    }

    public TableDTO(Table table, byte[] qrCode) {
        super(table.getPlace(),table.getState(),table.getType(),table.getQrCode());
        setId(table.getId());
        this.file = qrCode;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
