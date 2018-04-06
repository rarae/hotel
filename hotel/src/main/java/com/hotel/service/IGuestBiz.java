package com.hotel.service;

import com.hotel.entity.Guest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGuestBiz {
    List<Guest> listAllGuest();
    List<Guest> listPagedGuest(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    int count();
    int updateGuest(Guest guest);
    int deleteGuest(Guest guest);
    int insertGuest(Guest guest);
    Guest getGuestById(Guest guest);

    List<Guest> queryGuest(String keyword);
}
