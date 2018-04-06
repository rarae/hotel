package com.hotel.service;

import com.hotel.entity.CheckOut;

import java.util.List;

public interface ICheckOutBiz {
    int insertCheckOut(com.hotel.entity.CheckOut checkOut);
    String getNo();
    List<CheckOut> getCheckOutByregistrationNo(String registrationNo);
}
