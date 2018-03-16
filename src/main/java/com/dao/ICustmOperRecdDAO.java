package com.dao;

import com.bean.CustmOperRecord;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 用户操作记录DAO
 */
public interface ICustmOperRecdDAO {

    //查询记录
    CustmOperRecord[] queryRecord(@Param("no") String no);
    CustmOperRecord[] queryRecordByType(@Param("no") String no, @Param("operType") Integer type);
    CustmOperRecord[] queryRecordByDate(@Param("no") String no, @Param("startDate")Date startDate, @Param("endDate") Date endDate);
    CustmOperRecord[] queryRecordByBoth(@Param("no") String no, @Param("operType") Integer type, @Param("startDate")Date startDate, @Param("endDate") Date endDate);


    // 插入记录
    void insertRecord(@Param("record") CustmOperRecord record);
}
