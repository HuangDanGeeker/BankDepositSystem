package com.service;

import com.dao.ICustomerDAO;
import com.dao.IStaffDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by HuangDanGeeker on 2018/3/22.
 */

@Service
public class CommonService {

    @Resource
    private ICustomerDAO customerDAO;
    @Resource
    private IStaffDAO staffDAO;

}
