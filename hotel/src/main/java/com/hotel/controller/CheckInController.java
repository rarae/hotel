package com.hotel.controller;

import com.hotel.entity.*;
import com.hotel.service.*;
import com.hotel.utils.DateUtil;
import com.hotel.utils.TrimUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class CheckInController {
    @Autowired
    private ICheckInBiz checkInBiz;
    @Autowired
    private IGuestBiz guestBiz;
    @Autowired
    private IGuestRoomBiz guestRoomBiz;
    @Autowired
    private IRegistrationCardBiz registrationCardBiz;
    @Autowired
    private IAdvanceOrderBiz advanceOrderBiz;


    @RequestMapping(value="insertCheckIn.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertCheckIn(@RequestParam("guest1") String guest1,
                         @RequestParam("guest2") String guest2,
                         @RequestParam("guest3") String guest3,
                         @RequestParam("guest4") String guest4,
                         @RequestParam("guest5") String guest5,
                      @RequestParam("startTime") String checkInTime,
                      @RequestParam("endTime") String checkOutTime,
                      @RequestParam("no") String roomNo,
                      @RequestParam("note") String note,
                      HttpServletRequest request){
        Guest guest = new Guest();
        guest.setID(guest1);
        if(guestBiz.getGuestById(guest)==null){
            return "no such guest";
        }
        if(!guest2.equals("")){
            guest.setID(guest2);
            if(guestBiz.getGuestById(guest)==null){
                return "no such guest";
            }
        }
        if(!guest3.equals("")){
            guest.setID(guest3);
            if(guestBiz.getGuestById(guest)==null){
                return "no such guest";
            }
        }
        if(!guest4.equals("")){
            guest.setID(guest4);
            if(guestBiz.getGuestById(guest)==null){
                return "no such guest";
            }
        }
        if(!guest5.equals("")){
            guest.setID(guest5);
            if(guestBiz.getGuestById(guest)==null){
                return "no such guest";
            }
        }

        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(roomNo);
        List<GuestRoom> guestRoomList = guestRoomBiz.getGuestRoomByNo(guestRoom);

        if(guestRoomList==null){
            return "no such room";
        }

        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);

        //room you check in
        guestRoom = guestRoomList.get(0);
        guestRoom.setStatus("inUse");
        //change room status
        try{
            guestRoomBiz.updateGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        //store checkin
        CheckIn checkIn = new CheckIn();

        try {
            String no = checkInBiz.getNo();
            if(no==null){
                checkIn.setNo("10001");
            }
            else{
                no = String.valueOf(Integer.valueOf(no.trim())+1);
                checkIn.setNo(no);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        checkIn.setGuestID(guest1);

        String startTime = DateUtil.date2TimeStamp(checkInTime, "yyyy-MM-dd HH:mm:ss");
        String endTime = DateUtil.date2TimeStamp(checkOutTime, "yyyy-MM-dd HH:mm:ss");
        checkIn.setCheckInTime(startTime);
        checkIn.setCheckOutTime(endTime);
        int ints = Integer.valueOf(startTime);
        int inte = Integer.valueOf(endTime);
        int intResult = inte - ints;
        checkIn.setDuration(String.valueOf(intResult));

        checkIn.setRoomNo(roomNo);
        checkIn.setDeposit( String.valueOf((int)(Integer.valueOf(guestRoom.getPrice().trim())*DateUtil.duration2days(checkIn.getDuration())*1.2)) );

        checkIn.setAdminID(request.getSession().getAttribute("username").toString().trim());
        checkIn.setConfirmTime(DateUtil.timeStamp().trim());
        checkIn.setNote(note);

        try {
            checkInBiz.insertCheckIn(checkIn);
            RegistrationCard registrationCard = new RegistrationCard();
            registrationCard.setRegistrationNo(checkIn.getNo());

            //guest1
            registrationCard.setGuestId(guest1);
            registrationCardBiz.insertRegistrationCard(registrationCard);

            //guest2
            if(!guest2.equals("")) {
                registrationCard.setGuestId(guest2);
                registrationCardBiz.insertRegistrationCard(registrationCard);
            }
            //guest3
            if(!guest3.equals("")) {
                registrationCard.setGuestId(guest3);
                registrationCardBiz.insertRegistrationCard(registrationCard);
            }
            //guest4
            if(!guest4.equals("")) {
                registrationCard.setGuestId(guest4);
                registrationCardBiz.insertRegistrationCard(registrationCard);
            }
            //guest2
            if(!guest5.equals("")) {
                registrationCard.setGuestId(guest5);
                registrationCardBiz.insertRegistrationCard(registrationCard);
            }


        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }


        return "success";
    }


    @RequestMapping(value="listLatestCheckIn.do", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listLatestCheckIn(@RequestParam("roomNo") String roomNo,
                         HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        //入住单
        CheckIn checkIn = new CheckIn();
        checkIn.setRoomNo(roomNo);

        //客房信息
        GuestRoom guestRoom = new GuestRoom();
        List<CheckIn> checkIns;
        List<GuestRoom> guestRooms;

        try{
            List<AdvanceOrder> advanceOrders = advanceOrderBiz.getAdvanceOrderByRoomNo(roomNo);
            if(advanceOrders.size()>=1){
                advanceOrders = TrimUtil.stringTrimAdvanceOrder(advanceOrders);
                jsonObject.put("AdvanceOrder", advanceOrders);
            }


            checkIns = checkInBiz.getCheckInByRoomNo(checkIn);

            //入住单不为空
            if(checkIns.size()>=1){
                checkIns = TrimUtil.stringTrimCheckIn(checkIns);
                jsonObject.put("CheckIn", checkIns);

                CheckIn checkIn1 = checkIns.get(0);
                //入住登记表不为空
                List<RegistrationCard> registrationCards = registrationCardBiz.getRegistrationCardByregistrationNo(checkIn1.getNo());
                if(registrationCards.size()>=1){
                    registrationCards = TrimUtil.stringTrimRegistrationCard(registrationCards);
                    //取出证件号，到客户表中去找客户详情信息
                    List<Guest> guests = new ArrayList<Guest>();
                    for(RegistrationCard registrationCard : registrationCards){
                        Guest guest = new Guest();
                        guest.setID(registrationCard.getGuestId());
                        guest = guestBiz.getGuestById(guest);
                        guests.add(guest);
                    }

                    guests = TrimUtil.stringTrimGuest(guests);
                    jsonObject.put("Guest", guests);
                }
            }


            guestRoom.setNo(roomNo);

            guestRooms = guestRoomBiz.getGuestRoomByNo(guestRoom);
            guestRooms = TrimUtil.stringTrimGuestRoom(guestRooms);

            jsonObject.put("GuestRoom", guestRooms);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return jsonObject.toString();
    }

}
