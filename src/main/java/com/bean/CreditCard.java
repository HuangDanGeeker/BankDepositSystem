package com.bean;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/25.
 */
public class CreditCard {

    String no;
    Integer nums;
    String dueTime;
    Float intrest;


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

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public Float getIntrest() {
        return intrest;
    }

    public void setIntrest(Float intrest) {
        this.intrest = intrest;
    }


}
