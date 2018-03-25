package com.service;

import com.dao.ICreditCardDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Service("creditAccountService")
public class CreditAccountService {

    @Resource
    private ICreditCardDAO creditCardDAO;

    public void createCreditCard(String custmNo, String cardNo){
        creditCardDAO.createCreditCard(custmNo, cardNo);
    }

    public void deposit(String custmNo, String creditCardNo, Integer nums, String dutTime){
        creditCardDAO.deposit(custmNo, creditCardNo, nums, dutTime);
    }

    // 生成信用卡号
    public String generateCreditCardNo(){

        String num = creditCardDAO.getCreditCardNo();
        String newNum = String.valueOf(Integer.valueOf(num) + 1);
        creditCardDAO.addCreditCardNo(newNum);
        return num;
    }



}
