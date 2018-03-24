package com.service;

import com.bean.Staff;
import com.dao.IStaffDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Service("staffService")
public class StaffService
{
    @Resource
    private IStaffDAO staffDAO;

    public boolean checkStaff(String custmNo, String custmPasswd, String custmName){
        List<Staff> staffList = staffDAO.checkStaff(custmName, custmNo, custmPasswd);
        return (0 != staffList.size());
    }

    public boolean regist(String name, Integer no, String passwd, String birthday, String phone){

        staffDAO.insertStaff(name, no, passwd, birthday, phone);

        return true;
    }


    public Integer generateNo(){
        return Integer.valueOf(staffDAO.generateNo())+1;
    }
}
