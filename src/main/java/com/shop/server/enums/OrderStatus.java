package com.shop.server.enums;

public enum OrderStatus {
    ALREADY_SENT("已送",(byte)0),
    BEGIN_SENT("正在派送",(byte)1),
    NOT_SENT("未送",(byte)2);
    private final String desc;
    private final Byte status;
    OrderStatus(String desc,Byte status){
        this.desc = desc;
        this.status = status;
    }

    public String desc() {
        return desc;
    }

    public Byte status() {
        return status;
    }
}
