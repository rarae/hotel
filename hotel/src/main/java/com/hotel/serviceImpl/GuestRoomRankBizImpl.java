package com.hotel.serviceImpl;

import com.hotel.entity.GuestRoomRank;
import com.hotel.mapper.GuestRoomRankMapper;
import com.hotel.service.IGuestRoomRankBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestRoomRankBizImpl implements IGuestRoomRankBiz{
    @Autowired
    private GuestRoomRankMapper guestRoomRankMapper;

    public List<GuestRoomRank> listAllGuestRoomRank() {
        return guestRoomRankMapper.listAllGuestRoomRank();
    }

    public List<GuestRoomRank> listPagedGuestRoomRankt(int pageIndex, int pageSize) {
        return guestRoomRankMapper.listPagedGuestRoomRankt(pageIndex, pageSize);
    }

    public int count() {
        return guestRoomRankMapper.count();
    }

    public int updateGuestRoomRank(GuestRoomRank guestRoomRank) {
        return guestRoomRankMapper.updateGuestRoomRank(guestRoomRank);
    }

    public int deleteGuestRoomRank(GuestRoomRank guestRoomRank) {
        return guestRoomRankMapper.deleteGuestRoomRank(guestRoomRank);
    }

    public int insertGuestRoomRank(GuestRoomRank guestRoomRank) {
        return guestRoomRankMapper.insertGuestRoomRank(guestRoomRank);
    }

    public GuestRoomRank getGuestRoomRankByRank(GuestRoomRank guestRoomRank) {
        return guestRoomRankMapper.getGuestRoomRankByRank(guestRoomRank);
    }

    public List<GuestRoomRank> queryGuestRoomRank(String keyword) {
        return guestRoomRankMapper.queryGuestRoomRank(keyword);
    }
}
