package com.hotel.service;

import com.hotel.entity.GuestRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGuestRoomBiz {
    List<GuestRoom> listAllGuestRoom();
    List<GuestRoom> listPagedGuestRoom(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    int count();
    int updateGuestRoom(GuestRoom guestRoom);
    int deleteGuestRoom(GuestRoom guestRoom);
    int insertGuestRoom(GuestRoom guestRoom);
    List<GuestRoom> getGuestRoomByNo(GuestRoom guestRoom);

    List<GuestRoom> queryGuestRoom(String keyword);
}
