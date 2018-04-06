package com.hotel.mapper;

import com.hotel.entity.RegistrationCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationCardMapper {
    int insertRegistrationCard(RegistrationCard registrationCard);
    List<RegistrationCard> getRegistrationCardByregistrationNo(String registrationNo);
}
