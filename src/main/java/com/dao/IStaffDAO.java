package com.dao;

import com.bean.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 */
@Repository
public interface IStaffDAO {

    // 插入员工信息
    void insertStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") String passwd, @Param("birthday") String birthday, @Param("phone") String phone, @Param("address") String address);

    // 删除员工信息
    void deleteStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone);

    // 更新员工信息
    void updateStaff(@Param("no") String no);

    // 查找员工信息
    List<Staff> checkStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") String passwd, @Param("birthday") String birthday);

    // 查询登录密码
    String queryPassWd(@Param("no") String no);

    // 修改登录密码
    void updatePassWd(@Param("no") String no, @Param("passwd") Integer passwd);

    // 生成账号
    String generateNo();

    //数据库账号自增
    void updateNo();
}
