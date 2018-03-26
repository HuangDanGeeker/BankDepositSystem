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

}
