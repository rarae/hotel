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
public class CheckOutController {
    @Autowired
    private ICheckInBiz checkInBiz;
    @Autowired
    private IGuestBiz guestBiz;
    @Autowired
    private IGuestRoomBiz guestRoomBiz;
    @Autowired
    private IRegistrationCardBiz registrationCardBiz;
    @Autowired
    private ICheckOutBiz checkOutBiz;

    @RequestMapping(value="insertCheckOut.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertCheckIn(@RequestParam("registrationNo") String registrationNo,
                         @RequestParam("note") String note,
                         HttpServletRequest request){

        CheckOut checkOut = new CheckOut();

        //set no
        try {
            String no = checkOutBiz.getNo();
            if(no==null){
                checkOut.setNo("20001");
            }
            else{
                no = String.valueOf(Integer.valueOf(no.trim())+1);
                checkOut.setNo(no);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        checkOut.setRegistrationNo(registrationNo);

        //get checkIn by registrationNo
        try{
            List<CheckIn> checkIns = checkInBiz.getCheckInByNo(registrationNo);

            //trim
            checkIns = TrimUtil.stringTrimCheckIn(checkIns);
            CheckIn checkIn = checkIns.get(0);

            int amout = (int)(Integer.valueOf(checkIn.getDeposit())/1.2);
            int refund = Integer.valueOf(checkIn.getDeposit()) - amout;
            checkOut.setAmount( String.valueOf(amout) );
            checkOut.setRefund( String.valueOf(refund) );

            checkOut.setAdminID(request.getSession().getAttribute("username").toString().trim());
            checkOut.setConfirmTime(DateUtil.timeStamp().trim());
            checkOut.setNote(note);

            //store checkOut
            checkOutBiz.insertCheckOut(checkOut);

            //change the room status
            GuestRoom guestRoom = new GuestRoom();
            guestRoom.setNo(checkIn.getRoomNo());
            List<GuestRoom> guestRooms = guestRoomBiz.getGuestRoomByNo(guestRoom);
            guestRoom = guestRooms.get(0);

            if(guestRoom.getStatus().trim().equals("inUse")){
                guestRoom.setStatus("notInUse");
                guestRoomBiz.updateGuestRoom(guestRoom);
            }
            else{
                return "fail";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
       return "success";
    }


    @RequestMapping(value="checkOutInfo.do", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody
    String checkOut(@RequestParam("registrationNo") String registrationNo,
                    @RequestParam("roomNo") String roomNo,
                             HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        try {
            List<CheckOut> checkOuts = checkOutBiz.getCheckOutByregistrationNo(registrationNo);
            if (checkOuts.size() < 1) {
                return "fail";
            }
            //trim
            checkOuts = TrimUtil.stringTrimCheckOut(checkOuts);

            jsonObject.put("CheckOut", checkOuts);

            GuestRoom guestRoom = new GuestRoom();
            guestRoom.setNo(roomNo);

            List<GuestRoom> guestRooms = guestRoomBiz.getGuestRoomByNo(guestRoom);
            guestRooms = TrimUtil.stringTrimGuestRoom(guestRooms);
            jsonObject.put("GuestRoom", guestRooms);

            List<CheckIn> checkIns = checkInBiz.getCheckInByNo(registrationNo.trim());
            checkIns = TrimUtil.stringTrimCheckIn(checkIns);
            jsonObject.put("CheckIn", checkIns);

            List<RegistrationCard> registrationCards = registrationCardBiz.getRegistrationCardByregistrationNo(registrationNo);
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
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return jsonObject.toString();
    }

}
