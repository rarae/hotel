package com.hotel.service;

import com.hotel.entity.Admin;

public interface IAdminBiz {
    Admin getAdminFromUsername(String username);
    void changePassword(String username);
}
