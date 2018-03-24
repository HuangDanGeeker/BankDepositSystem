package com.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Repository
public interface ICreditCardDAO {

    //创建客户信用卡账号
    void createCreditCard(@Param("custmNo") String custmNo, @Param("cardNo") String cardNo);

    // 存取款(获取&&定期)、
    void deposit(String custmNo, String creditCardNo, Integer nums, String dutTime);
}
