package com.hotel.mapper;

import com.hotel.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    Admin getAdminFromUsername(String username);
    void changePassword(String username);
}
