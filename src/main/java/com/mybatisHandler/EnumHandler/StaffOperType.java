package com.mybatisHandler.EnumHandler;

/**
 * Created by HuangDanGeeker on 2018/3/26.
 */
public enum StaffOperType implements BasicCodeEnum{
    Deposit(10), Draw(20), Regist(25), ChaPass(26), Login(30);
    private int code;

    StaffOperType(int code){
        this.code = code;
    }

    public int getCode(){

        return this.code;
    }
}
