package com.bean;

import com.enums.CustmOperType;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 客户操作记录表条目
 * Record clause of Staff Operation
 */
public class CustmOperRecord {
    String no;
    CustmOperType operType;
    String dealerNo;
    String dealerName;
    Integer nums;
    Date operDate;

    public String getDealerNo() {
        return dealerNo;
    }

    public void setDealerNo(String dealerNo) {
        this.dealerNo = dealerNo;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }


    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    String dealer = "self";

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public CustmOperType getOperType() {
        return operType;
    }

    public void setOperType(CustmOperType operType) {
        this.operType = operType;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }


}
