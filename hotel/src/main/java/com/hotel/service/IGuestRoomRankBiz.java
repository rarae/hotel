package com.hotel.service;

import com.hotel.entity.GuestRoomRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGuestRoomRankBiz {
    List<GuestRoomRank> listAllGuestRoomRank();
    List<GuestRoomRank> listPagedGuestRoomRankt(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    int count();
    int updateGuestRoomRank(GuestRoomRank guestRoomRank);
    int deleteGuestRoomRank(GuestRoomRank guestRoomRank);
    int insertGuestRoomRank(GuestRoomRank guestRoomRank);
    GuestRoomRank getGuestRoomRankByRank(GuestRoomRank guestRoomRank);

    List<GuestRoomRank> queryGuestRoomRank(String keyword);
}
