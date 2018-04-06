package com.hotel.serviceImpl;

import com.hotel.entity.AdvanceOrder;
import com.hotel.mapper.AdvanceOrderMapper;
import com.hotel.service.IAdvanceOrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvanceOrderBizImpl implements IAdvanceOrderBiz {
    @Autowired
    private AdvanceOrderMapper advanceOrderMapper;
    @Override
    public int insertAdvanceOrder(AdvanceOrder advanceOrder) {
        return advanceOrderMapper.insertAdvanceOrder(advanceOrder);
    }

    @Override
    public String getNo() {
        return advanceOrderMapper.getNo();
    }

    @Override
    public List<AdvanceOrder> getAdvanceOrderByRoomNo(String roomNo) {
        return advanceOrderMapper.getAdvanceOrderByRoomNo(roomNo);
    }
}
