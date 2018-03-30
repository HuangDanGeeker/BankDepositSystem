package com.service;

import com.bean.StaffOperRecord;
import com.dao.IStaffOperRecdDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Service("staffOperationService")
public class StaffOperationService {
    @Resource
    private IStaffOperRecdDAO staffOperRecdDAO;

    public List<StaffOperRecord> queryRecord(String staff){
        return staffOperRecdDAO.queryRecord(staff, null);
    }

    public void createStaffOperTable(String no){

        staffOperRecdDAO.createStaffOperTable(no);
    }

    public void insertRecord(String staffNo, String operType, String custmNo, String custmName, String creditCardNo, Integer nums){
        //空值处理
        operType = operType == null ? "unknown type" : operType;
        custmNo = custmNo == null ? "null" : custmNo;
        custmName = custmName == null ? "null" : custmName;
        creditCardNo = creditCardNo == null ? "null" : creditCardNo;
        nums = nums == null ? 0 : nums;

        staffOperRecdDAO.insertRecord(staffNo, operType, custmNo, custmName, creditCardNo, nums);
    }
}
