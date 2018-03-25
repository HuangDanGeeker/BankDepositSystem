package com.bean;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/25.
 */
public class CreditCard {

    String no;
    Integer nums;
    Date dueTime;
    Integer intrest;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getIntrest() {
        return intrest;
    }

    public void setIntrest(Integer intrest) {
        this.intrest = intrest;
    }


}
