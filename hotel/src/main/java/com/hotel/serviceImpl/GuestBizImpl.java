package com.hotel.serviceImpl;

import com.hotel.entity.Guest;
import com.hotel.mapper.GuestMapper;
import com.hotel.service.IGuestBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBizImpl implements IGuestBiz {
    @Autowired
    private GuestMapper guestMapper;

    public List<Guest> listAllGuest() {
        return guestMapper.listAllGuest();
    }

    public List<Guest> listPagedGuest(int pageIndex, int pageSize) {
        return guestMapper.listPagedGuest(pageIndex, pageSize);
    }

    public int count() {
        return guestMapper.count();
    }

    public int updateGuest(Guest guest) {
        return guestMapper.updateGuest(guest);
    }

    public int deleteGuest(Guest guest) {
        return guestMapper.deleteGuest(guest);
    }

    public int insertGuest(Guest guest) {
        return guestMapper.insertGuest(guest);
    }

    public Guest getGuestById(Guest guest) {
        return guestMapper.getGuestById(guest);
    }

    public List<Guest> queryGuest(String keyword){
        return guestMapper.queryGuest(keyword);
    }
}
