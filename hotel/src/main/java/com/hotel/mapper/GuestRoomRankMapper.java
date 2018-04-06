package com.hotel.mapper;

import com.hotel.entity.GuestRoomRank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRoomRankMapper {
    List<GuestRoomRank> listAllGuestRoomRank();
    List<GuestRoomRank> listPagedGuestRoomRankt(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    int count();
    int updateGuestRoomRank(GuestRoomRank guestRoomRank);
    int deleteGuestRoomRank(GuestRoomRank guestRoomRank);
    int insertGuestRoomRank(GuestRoomRank guestRoomRank);
    GuestRoomRank getGuestRoomRankByRank(GuestRoomRank guestRoomRank);

    List<GuestRoomRank> queryGuestRoomRank(String keyword);
}
