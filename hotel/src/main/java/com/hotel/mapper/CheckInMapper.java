package com.hotel.mapper;

import com.hotel.entity.CheckIn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInMapper {
    int insertCheckIn(com.hotel.entity.CheckIn checkIn);
    String getNo();
    String getLatestNoByRoomNo(String roomNo);
    List<CheckIn> getCheckInByRoomNo(CheckIn checkIn);
    List<CheckIn> getCheckInByNo(String no);
}
