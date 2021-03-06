package com.dao;

import com.bean.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 客户DAO
 */
@Repository
public interface ICustomerDAO {

    // 插入客户
    void insertCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") String passwd, @Param("birthday") String birthday, @Param("phone") String phone, @Param("address") String address);

    // 更新客户
    void updateCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone, @Param("address") String address);

    // 删除客户
    void deleteCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone, @Param("address") String address);

    // 查询客户
    List<Customer> queryCustomer(@Param("no") String no, @Param("passwd") String custmPasswd, @Param("name") String name, @Param("birthday") String birthday);
    String queryCustomerName(@Param("no") String no);
    // 查询密码
    Integer queryPassWd(@Param("no") String no, @Param("passwd") Integer passwd);

    // 修改密码
    Integer updatePassWd(@Param("no") String no, @Param("passwd") Integer passwd);

    //产生账号
    String generateNo();
    //数据库账号自增
    void updateNo();
}
