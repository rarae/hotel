package com.hotel.utils;

import com.hotel.entity.*;

import java.util.List;

public class TrimUtil {
    //    GuestRoomRank
    public static List<GuestRoomRank> stringTrimGuestRoomRank(List<GuestRoomRank> guestRoomRanks) {
        for (GuestRoomRank guestRoomRank : guestRoomRanks) {
            guestRoomRank.setRank(guestRoomRank.getRank().trim());
            guestRoomRank.setNumOfPeople(guestRoomRank.getNumOfPeople().trim());
            guestRoomRank.setPrice(guestRoomRank.getPrice().trim());
            guestRoomRank.setFacility(guestRoomRank.getFacility().trim());
        }

        return guestRoomRanks;
    }

    public static List<GuestRoomRank> makeStringRed(String keyword, List<GuestRoomRank> guestRoomRanks) {
        for (GuestRoomRank guestRoomRank : guestRoomRanks) {
            guestRoomRank.setRank(guestRoomRank.getRank().replace(keyword, addRedTable(keyword)));
            guestRoomRank.setNumOfPeople(guestRoomRank.getNumOfPeople().replace(keyword, addRedTable(keyword)));
            guestRoomRank.setPrice(guestRoomRank.getPrice().replace(keyword, addRedTable(keyword)));
            guestRoomRank.setFacility(guestRoomRank.getFacility().replace(keyword, addRedTable(keyword)));
        }

        return guestRoomRanks;
    }

    public static String addRedTable(String keyword) {
        return "<font color='red' >" + keyword + "</font>";
    }

    //    GuestRoom
    public static List<GuestRoom> stringTrimGuestRoom(List<GuestRoom> guestRooms) {
        for (GuestRoom guestRoom : guestRooms) {
            guestRoom.setNo(guestRoom.getNo().trim());
            guestRoom.setStatus(guestRoom.getStatus().trim());
            guestRoom.setLocation(guestRoom.getLocation().trim());
            guestRoom.setFloor(guestRoom.getFloor().trim());
            guestRoom.setArea(guestRoom.getArea().trim());
            guestRoom.setRank(guestRoom.getRank().trim());
            guestRoom.setNumOfPeople(guestRoom.getNumOfPeople().trim());
            guestRoom.setPrice(guestRoom.getPrice().trim());
            guestRoom.setFacility(guestRoom.getFacility().trim());
        }
        return guestRooms;
    }


    //Guest
    public static List<Guest> stringTrimGuest(List<Guest> guests) {
        for (Guest guest : guests) {
            guest.setIDType(guest.getIDType().trim());
            guest.setID(guest.getID().trim());
            guest.setName(guest.getName().trim());
            guest.setSex(guest.getSex().trim());
            guest.setAge(guest.getAge().trim());
            guest.setPhone(guest.getPhone().trim());
            guest.setAddress(guest.getAddress().trim());
            guest.setNote(guest.getNote().trim());
        }

        return guests;
    }

    public static List<Guest> makeStringRedGuest(String keyword, List<Guest> guests) {
        for (Guest guest : guests) {
            guest.setIDType(guest.getIDType().replace(keyword, addRedTable(keyword)));
            guest.setID(guest.getID().replace(keyword, addRedTable(keyword)));
            guest.setName(guest.getName().replace(keyword, addRedTable(keyword)));
            guest.setSex(guest.getSex().replace(keyword, addRedTable(keyword)));
            guest.setAge(guest.getAge().replace(keyword, addRedTable(keyword)));
            guest.setPhone(guest.getPhone().replace(keyword, addRedTable(keyword)));
            guest.setAddress(guest.getAddress().replace(keyword, addRedTable(keyword)));
            guest.setNote(guest.getNote().replace(keyword, addRedTable(keyword)));
        }

        return guests;
    }

    //CHECK IN
    public static List<CheckIn> stringTrimCheckIn(List<CheckIn> checkIns) {
        for (CheckIn checkIn : checkIns) {
            checkIn.setNo(checkIn.getNo().trim());
            checkIn.setGuestID(checkIn.getGuestID().trim());

            checkIn.setCheckInTime(DateUtil.timeStamp2Date(checkIn.getCheckInTime().trim(), "yyyy-MM-dd HH:mm:ss"));
            checkIn.setCheckOutTime(DateUtil.timeStamp2Date(checkIn.getCheckOutTime().trim(), "yyyy-MM-dd HH:mm:ss"));

            checkIn.setDuration(checkIn.getDuration().trim());
            checkIn.setRoomNo(checkIn.getRoomNo().trim());
            checkIn.setDeposit(checkIn.getDeposit().trim());
            checkIn.setAdminID(checkIn.getAdminID().trim());

            checkIn.setConfirmTime(DateUtil.timeStamp2Date(checkIn.getConfirmTime().trim(), "yyyy-MM-dd HH:mm:ss"));
            checkIn.setNote(checkIn.getNote().trim());

        }
        return checkIns;
    }

    //    RegistrationCard
    public static List<RegistrationCard> stringTrimRegistrationCard(List<RegistrationCard> registrationCards) {
        for (RegistrationCard registrationCard : registrationCards) {
            registrationCard.setGuestId(registrationCard.getGuestId().trim());
            registrationCard.setRegistrationNo(registrationCard.getRegistrationNo().trim());
        }
        return registrationCards;
    }

    //CheckOut
    public static List<CheckOut> stringTrimCheckOut(List<CheckOut> checkOuts) {
        for (CheckOut checkOut : checkOuts) {
            checkOut.setNo(checkOut.getNo().trim());
            checkOut.setRegistrationNo(checkOut.getRegistrationNo().trim());

            checkOut.setAmount(checkOut.getAmount().trim());
            checkOut.setRefund(checkOut.getRefund().trim());

            checkOut.setAdminID(checkOut.getAdminID().trim());

            checkOut.setConfirmTime(DateUtil.timeStamp2Date(checkOut.getConfirmTime().trim(), "yyyy-MM-dd HH:mm:ss"));
            checkOut.setNote(checkOut.getNote().trim());

        }
        return checkOuts;
    }

    //AdvanceOrder
    public static List<AdvanceOrder> stringTrimAdvanceOrder(List<AdvanceOrder> advanceOrders) {
        for (AdvanceOrder advanceOrder : advanceOrders) {
            advanceOrder.setNo(advanceOrder.getNo().trim());
            advanceOrder.setGuestID(advanceOrder.getGuestID().trim());

            advanceOrder.setCheckInTime(DateUtil.timeStamp2Date(advanceOrder.getCheckInTime().trim(), "yyyy-MM-dd HH:mm:ss"));
            advanceOrder.setCheckOutTime(DateUtil.timeStamp2Date(advanceOrder.getCheckOutTime().trim(), "yyyy-MM-dd HH:mm:ss"));

            advanceOrder.setDuration(advanceOrder.getDuration().trim());
            advanceOrder.setRoomNo(advanceOrder.getRoomNo().trim());
            advanceOrder.setDeposit(advanceOrder.getDeposit().trim());
            advanceOrder.setAdminID(advanceOrder.getAdminID().trim());

            advanceOrder.setConfirmTime(DateUtil.timeStamp2Date(advanceOrder.getConfirmTime().trim(), "yyyy-MM-dd HH:mm:ss"));
            advanceOrder.setNote(advanceOrder.getNote().trim());

        }
        return advanceOrders;
    }
}
