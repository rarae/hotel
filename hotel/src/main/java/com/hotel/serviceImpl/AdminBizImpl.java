package com.hotel.serviceImpl;

import com.hotel.entity.Admin;
import com.hotel.mapper.AdminMapper;
import com.hotel.service.IAdminBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBizImpl implements IAdminBiz {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminFromUsername(String username) {
        return  adminMapper.getAdminFromUsername(username);
    }

    public void changePassword(String username) {

    }
}
