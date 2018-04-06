package com.hotel.service;

import com.hotel.entity.RegistrationCard;

import java.util.List;

public interface IRegistrationCardBiz {
    int insertRegistrationCard(RegistrationCard registrationCard);
    List<RegistrationCard> getRegistrationCardByregistrationNo(String registrationNo);
}
