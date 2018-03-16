package com.dao;

import com.bean.Staff;
import org.apache.ibatis.annotations.Param;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 */
public interface IStaffDAO {

    // 插入员工信息
    void insertStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone);
    void insertStaff(@Param("staff") Staff staff);

    // 删除员工信息
    void deleteStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone);
    void deleteStaff(@Param("staff") Staff staff);

    // 更新员工信息
    void updateStaff(@Param("no") String no);
    void updateStaff(@Param("staff") Staff staff);

    // 查询员工信息
    void queryStaff(@Param("name") String name, @Param("no") String no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone);
    void queryStaff(@Param("staff") Staff staff);

    // 查询登录密码
    String queryPassWd(@Param("no") String no);
    String queryPassWd(@Param("staff") Staff staff);

    // 修改登录密码
    void updatePassWd(@Param("no") String no, @Param("passwd") Integer passwd);
    void updatePassWd(@Param("staff") Staff staff);

}
