package com.hotel.mapper;

import com.hotel.entity.CheckOut;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutMapper {
    int insertCheckOut(com.hotel.entity.CheckOut checkOut);
    String getNo();
    List<CheckOut> getCheckOutByregistrationNo(String registrationNo);
}
