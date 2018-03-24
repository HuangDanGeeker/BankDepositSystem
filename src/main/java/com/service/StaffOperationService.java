package com.service;

import com.dao.IStaffOperRecdDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by HuangDanGeeker on 2018/3/23.
 */
@Service("staffOperationService")
public class StaffOperationService {
    @Resource
    private IStaffOperRecdDAO staffOperRecdDAO;





}
