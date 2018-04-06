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
public class AdvanceOrderController {
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


    @RequestMapping(value="insertAdvanceOrder.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertAdvanceOrder(@RequestParam("guest1") String guest1,

                         @RequestParam("startTime") String checkInTime,
                         @RequestParam("endTime") String checkOutTime,
                         @RequestParam("no") String roomNo,
                         @RequestParam("note") String note,
                         HttpServletRequest request){
        // check the guest
        Guest guest = new Guest();
        guest.setID(guest1);
        if(guestBiz.getGuestById(guest)==null){
            return "no such guest";
        }
        // check the guestRoom
        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(roomNo);
        List<GuestRoom> guestRoomList = guestRoomBiz.getGuestRoomByNo(guestRoom);

        if(guestRoomList==null){
            return "no such room";
        }
        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);

        //room you check in
        guestRoom = guestRoomList.get(0);
        guestRoom.setStatus("booked");

        //change room status
        try{
            guestRoomBiz.updateGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        //store AdvanceOrder
        AdvanceOrder advanceOrder = new AdvanceOrder();

        try {
            String no = advanceOrderBiz.getNo();
            if(no==null){
                advanceOrder.setNo("30001");
            }
            else{
                no = String.valueOf(Integer.valueOf(no.trim())+1);
                advanceOrder.setNo(no);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        advanceOrder.setGuestID(guest1);

        String startTime = DateUtil.date2TimeStamp(checkInTime, "yyyy-MM-dd HH:mm:ss");
        String endTime = DateUtil.date2TimeStamp(checkOutTime, "yyyy-MM-dd HH:mm:ss");
        advanceOrder.setCheckInTime(startTime);
        advanceOrder.setCheckOutTime(endTime);
        int ints = Integer.valueOf(startTime);
        int inte = Integer.valueOf(endTime);
        int intResult = inte - ints;
        advanceOrder.setDuration(String.valueOf(intResult));

        advanceOrder.setRoomNo(roomNo);
        advanceOrder.setDeposit( String.valueOf((int)(Integer.valueOf(guestRoom.getPrice().trim())*DateUtil.duration2days(advanceOrder.getDuration())*1.2)) );

        advanceOrder.setAdminID(request.getSession().getAttribute("username").toString().trim());
        advanceOrder.setConfirmTime(DateUtil.timeStamp().trim());
        advanceOrder.setNote(note);

        try {
            advanceOrderBiz.insertAdvanceOrder(advanceOrder);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }


    @RequestMapping(value="listLatestAdvanceOrder.do", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listLatestAdvanceOrder(@RequestParam("roomNo") String roomNo,
                             HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        //客房信息
        GuestRoom guestRoom = new GuestRoom();

        List<GuestRoom> guestRooms;

        try{



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

    @RequestMapping(value="canceltAdvanceOrder.do", method = RequestMethod.POST)
    public @ResponseBody
    String canceltAdvanceOrder(@RequestParam("roomNo") String roomNo,
                               HttpServletRequest request){
        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(roomNo);
        List<GuestRoom> guestRoomList = guestRoomBiz.getGuestRoomByNo(guestRoom);

        if(guestRoomList==null){
            return "no such room";
        }
        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);



        //room you check in
        guestRoom = guestRoomList.get(0);
        guestRoom.setStatus("notInUse");

        try{
            guestRoomBiz.updateGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

}
