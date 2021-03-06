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

    void createCreditCardTable(@Param("no") String no);

    //创建客户信用卡账号
    void createCreditCard(@Param("custmNo") String custmNo, @Param("cardNo") String cardNo);

    // 存款(获取&&定期)、
    void deposit(@Param("custmNo") String custmNo, @Param("basicIntrest") Float basicInterest, @Param("cardNo") String creditCardNo, @Param("nums") Integer nums, @Param("dueTime") String dutTime);
    // 取款(获取&&定期)、
    void require(@Param("custmNo") String custmNo, @Param("basicIntrest") Float basicInterest, @Param("cardNo") String creditCardNo, @Param("nums") Integer nums);

    //获取最近产生的信用卡号
    String getCreditCardNo();
    //更新最近产生的信用卡号
    void addCreditCardNo(@Param("newNum") String newNum);

    List<CreditCard> queryCreditCardRecord(@Param("cardNo") String no);

    Float queryBasicIntrest();

    String queryDueTime(@Param("custmNo") String custmNo, @Param("creditCardNo") String creditCardNo);
}
