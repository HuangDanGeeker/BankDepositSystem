package com.dao;

import com.bean.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 * 客户DAO
 */
public interface ICustomerDAO {

    // 插入客户
    void insertCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone, @Param("address") String address);
    void insertCustomer(@Param("customer")Customer customer);

    // 更新客户
    void updateCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone, @Param("address") String address);
    void updateCustomer(@Param("customer")Customer customer);

    // 删除客户
    void deleteCustomer(@Param("name") String name, @Param("no") Integer no, @Param("passwd") Integer passwd, @Param("birthday") Integer birthday, @Param("phone") String phone, @Param("address") String address);
    void deleteCustomer(@Param("customer")Customer customer);

    // 查询客户
    Customer queryCustomer(@Param("no") Integer no);
    Customer queryCustomer(@Param("customer")Customer customer);

    // 查询密码
    Integer queryPassWd(@Param("no") String no, @Param("passwd") Integer passwd);
    Integer queryPassWd(@Param("customer") Customer customer);

    // 修改密码
    Integer updatePassWd(@Param("no") String no, @Param("passwd") Integer passwd);
    Integer updatePassWd(@Param("customer") Customer customer);


}
