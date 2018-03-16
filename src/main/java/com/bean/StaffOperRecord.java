package com.bean;

import com.enums.StaffOperType;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 柜员操作记录条目
 * Record clause of Staff Operation
 */
public class StaffOperRecord {
    String no;
    StaffOperType operType;
    String custmNo;
    String custmName;
    Integer nums;
    Date operDate;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public Date getDate() {
        return operDate;
    }

    public void setDate(Date date) {
        this.operDate = date;
    }


}
