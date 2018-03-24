package com.service;

import com.bean.Customer;
import com.dao.ICustomerDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuangDanGeeker on 2018/3/22.
 */

@Service("customerService")
public class CustomerService {

    @Resource
    ICustomerDAO customerDAO;


    public boolean cheakCustomer(String custmNo, String custmPasswd){

        return (null == customerDAO.queryCustomer(custmNo, custmPasswd, null));

    }
    public boolean cheakCustomer(String custmNo, String custmPasswd, String custmName){

        List<Customer> customerList = customerDAO.queryCustomer(custmNo, custmPasswd, custmName);
        return (0 != customerList.size());

    }


    public List<String> checkCustomer(String custmName){

        List<Customer> result = customerDAO.queryCustomer(null, null, custmName);

        //use java1.8 stream
        return result.stream().map(n -> n.getNo()).collect(Collectors.toList());
    }


    public boolean regist(String name, Integer no, String passwd, String birthday, String phone, String address){

        customerDAO.insertCustomer(name, no, passwd, birthday, phone, address);

        return true;
    }

    public Integer generateNo(){
        return Integer.valueOf(customerDAO.generateNo()) + 1;
    }


}
