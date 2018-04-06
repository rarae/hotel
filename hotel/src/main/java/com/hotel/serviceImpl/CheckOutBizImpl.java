package com.hotel.serviceImpl;

import com.hotel.entity.CheckOut;
import com.hotel.mapper.CheckOutMapper;
import com.hotel.service.ICheckOutBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CheckOutBizImpl implements ICheckOutBiz {
    @Autowired
    private CheckOutMapper checkOutMapper;

    @Override
    public int insertCheckOut(CheckOut checkOut) {
        return checkOutMapper.insertCheckOut(checkOut);
    }

    @Override
    public String getNo() {
        return checkOutMapper.getNo();
    }

    @Override
    public List<CheckOut> getCheckOutByregistrationNo(String registrationNo) {
        return checkOutMapper.getCheckOutByregistrationNo(registrationNo);
    }
}
