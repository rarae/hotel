package com.hotel.mapper;

import com.hotel.entity.Guest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestMapper {
    List<Guest> listAllGuest();
    List<Guest> listPagedGuest(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    int count();
    int updateGuest(Guest guest);
    int deleteGuest(Guest guest);
    int insertGuest(Guest guest);
    Guest getGuestById(Guest guest);

    List<Guest> queryGuest(String keyword);
}
