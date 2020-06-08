package com.jyc.o2o_demo.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Response的JSON格式
 */
public class Msg implements Serializable {
    private int code;  // 状态码
    private String Message;  // 描述
    private Map<String,Object> data = new HashMap<String, Object>();  // 数据

    public Msg() {
    }

    public Msg(int code, String message) {
        this.code = code;
        Message = message;
    }

    public void setCM(int code,String message) {
        this.code = code;
        this.Message = message;
    }

    public void putData(String key, Object value) {
        this.data.put(key,value);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
