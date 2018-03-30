package com.dao;

import com.bean.StaffOperRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 柜员操作记录DAO
 */
@Repository
public interface IStaffOperRecdDAO {

    //查询记录
    //TODO , @Param("startDate")Date startDate, @Param("endDate") Date endDate
    List<StaffOperRecord> queryRecord(@Param("no") String no, @Param("operType") Integer type);

    void createStaffOperTable(@Param("no") String no);

    //添加纪录
    void insertRecord(@Param("staffNo") String staffNo, @Param("operType") String operType, @Param("custmNo") String custmNo, @Param("custmName") String custmName, @Param("creditCardNo") String creditCardNo, @Param("nums") Integer nums);
}
