package com.hotel.service;

import com.hotel.entity.CheckIn;

import java.util.List;

public interface ICheckInBiz {
    int insertCheckIn(CheckIn checkIn);
    String getNo();
    String getLatestNoByRoomNo(String roomNo);
    List<CheckIn> getCheckInByRoomNo(CheckIn checkIn);
    List<CheckIn> getCheckInByNo(String no);
}
