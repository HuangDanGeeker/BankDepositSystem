package com.service;

import com.dao.IStaffDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Service
public class StaffService
{
    @Resource
    private IStaffDAO staffDAO;

    public boolean checkStaff(String custmNo, String custmPasswd, String custmName){
        return staffDAO.checkStaff(custmNo, custmPasswd, custmName);
    }

    public boolean regist(String name, Integer no, String passwd, String birthday, String phone){

        staffDAO.insertStaff(name, no, passwd, birthday, phone);

        return true;
    }


    public Integer generateNo(){
        return staffDAO.generateNo();
    }
}
