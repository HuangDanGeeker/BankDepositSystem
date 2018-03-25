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



    public List<Customer> queryCustomer(String custmNo, String custmName){
       List<Customer> customerList = customerDAO.queryCustomer(custmNo, null, custmName);
       return customerList;
    }

    public boolean checkCustomer(String custmNo){

        List<Customer> customerList = customerDAO.queryCustomer(custmNo, null, null);
        return (0 != customerList.size());

    }
    public boolean checkCustomer(String custmNo, String custmPasswd, String custmName){

        List<Customer> customerList = customerDAO.queryCustomer(custmNo, custmPasswd, custmName);
        return (0 != customerList.size());

    }


    public List<String> checkCustomerNo(String custmName){

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
