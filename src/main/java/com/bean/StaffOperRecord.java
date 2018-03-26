package com.bean;

import com.mybatisHandler.EnumHandler.StaffOperType;

import java.time.LocalDate;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 柜员操作记录条目
 * Record clause of Staff Operation
 */
public class StaffOperRecord {
    StaffOperType operType;
    String custmNo;
    String custmName;
    String creditCardNo;
    Integer nums;
    String operDate;
    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }

    public StaffOperRecord(){
        operType = StaffOperType.Deposit;
        custmNo = "123";
        custmName = "123";
        nums = 10;
        operDate = LocalDate.now().toString();
    }


    public StaffOperType getOperType() {
        return operType;
    }

    public void setOperType(StaffOperType operType) {
        this.operType = operType;
    }

    public String getCustmNo() {
        return custmNo;
    }

    public void setCustmNo(String custmNo) {
        this.custmNo = custmNo;
    }

    public String getCustmName() {
        return custmName;
    }

    public void setCustmName(String custmName) {
        this.custmName = custmName;
    }
    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }



}
