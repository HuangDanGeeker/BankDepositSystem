package com.dao;

import com.bean.CreditCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Repository
public interface ICreditCardDAO {

    //创建客户信用卡账号
    void createCreditCard(@Param("custmNo") String custmNo, @Param("cardNo") String cardNo);

    // 存取款(获取&&定期)、
    void deposit(@Param("custmNo") String custmNo, @Param("cardNo") String creditCardNo, @Param("nums") Integer nums, @Param("dueTime") String dutTime);

    //获取最近产生的信用卡号
    String getCreditCardNo();
    //更新最近产生的信用卡号
    void addCreditCardNo(@Param("newNum") String newNum);

    List<CreditCard> queryCreditCardRecord(@Param("cardNo") String no);
}
