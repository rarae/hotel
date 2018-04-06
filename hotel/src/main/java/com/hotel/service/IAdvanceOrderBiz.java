package com.hotel.service;

import com.hotel.entity.AdvanceOrder;

import java.util.List;

public interface IAdvanceOrderBiz {
    int insertAdvanceOrder(AdvanceOrder advanceOrder);
    String getNo();
    List<AdvanceOrder> getAdvanceOrderByRoomNo(String roomNo);
}
