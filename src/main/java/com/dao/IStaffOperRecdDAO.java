package com.dao;

import com.bean.StaffOperRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 柜员操作记录DAO
 */
@Repository
public interface IStaffOperRecdDAO {

    //查询记录
    StaffOperRecord[] queryRecordByBoth(@Param("no") String no, @Param("operType") Integer type, @Param("startDate")Date startDate, @Param("endDate") Date endDate);

    //添加纪录
    void insertRecord(@Param("record") StaffOperRecord record);

}
