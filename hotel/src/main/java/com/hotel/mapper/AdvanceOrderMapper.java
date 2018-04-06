package com.hotel.mapper;

import com.hotel.entity.AdvanceOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvanceOrderMapper {
    int insertAdvanceOrder(AdvanceOrder advanceOrder);
    String getNo();
    List<AdvanceOrder> getAdvanceOrderByRoomNo(String roomNo);
}
