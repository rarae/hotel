package com.hotel.serviceImpl;

import com.hotel.entity.GuestRoom;
import com.hotel.entity.GuestRoomRank;
import com.hotel.mapper.GuestRoomMapper;
import com.hotel.service.IAdminBiz;
import com.hotel.service.IGuestRoomBiz;
import com.hotel.service.IGuestRoomRankBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestRoomBizImpl implements IGuestRoomBiz {
    @Autowired
    private GuestRoomMapper guestRoomMapper;


    public List<GuestRoom> listAllGuestRoom() {
        return guestRoomMapper.listAllGuestRoom();
    }

    public List<GuestRoom> listPagedGuestRoom(int pageIndex, int pageSize) {
        return guestRoomMapper.listPagedGuestRoom(pageIndex, pageSize);
    }

    public int count() {
        return guestRoomMapper.count();
    }

    public int updateGuestRoom(GuestRoom guestRoom) {
        return guestRoomMapper.updateGuestRoom(guestRoom);
    }

    public int deleteGuestRoom(GuestRoom guestRoom) {
        return guestRoomMapper.deleteGuestRoom(guestRoom);
    }

    public int insertGuestRoom(GuestRoom guestRoom) {
        return guestRoomMapper.insertGuestRoom(guestRoom);
    }

    public List<GuestRoom> getGuestRoomByNo(GuestRoom guestRoom) {
        return guestRoomMapper.getGuestRoomByNo(guestRoom);
    }

    public List<GuestRoom> queryGuestRoom(String keyword) {
        return null;
    }
}
