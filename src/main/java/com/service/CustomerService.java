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

@Service
public class CustomerService {

    @Resource
    private ICustomerDAO customerDAO;


    public boolean cheakCustomer(Integer custmNo, String custmPasswd){

        return (null == customerDAO.queryCustomer(custmNo, custmPasswd, null));

    }


    public List<String> checkCustomer(String custmName){

        List<Customer> result = customerDAO.queryCustomer(null, null, custmName);

        //use java1.8 stream
        return result.stream().map(n -> n.getNo()).collect(Collectors.toList());
    }





}
