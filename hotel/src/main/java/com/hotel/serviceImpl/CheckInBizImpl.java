package com.hotel.serviceImpl;

import com.hotel.entity.CheckIn;
import com.hotel.mapper.CheckInMapper;
import com.hotel.service.ICheckInBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInBizImpl implements ICheckInBiz {
    @Autowired
    private CheckInMapper checkInMapper;
    public int insertCheckIn(CheckIn checkIn) {
        return checkInMapper.insertCheckIn(checkIn);
    }

    public String getNo() {
        return checkInMapper.getNo();
    }

    @Override
    public String getLatestNoByRoomNo(String roomNo) {
        return checkInMapper.getLatestNoByRoomNo(roomNo);
    }

    @Override
    public List<CheckIn> getCheckInByRoomNo(CheckIn checkIn) {
        return checkInMapper.getCheckInByRoomNo(checkIn);
    }

    @Override
    public List<CheckIn> getCheckInByNo(String no) {
        return checkInMapper.getCheckInByNo(no);
    }
}
