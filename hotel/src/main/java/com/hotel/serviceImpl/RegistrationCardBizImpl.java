package com.hotel.serviceImpl;

import com.hotel.entity.RegistrationCard;
import com.hotel.mapper.RegistrationCardMapper;
import com.hotel.service.IRegistrationCardBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrationCardBizImpl implements IRegistrationCardBiz {
    @Autowired
    private RegistrationCardMapper registrationCardMapper;
    @Override
    public int insertRegistrationCard(RegistrationCard registrationCard) {
        return registrationCardMapper.insertRegistrationCard(registrationCard);
    }

    @Override
    public List<RegistrationCard> getRegistrationCardByregistrationNo(String registrationNo) {
        return registrationCardMapper.getRegistrationCardByregistrationNo(registrationNo);
    }
}
