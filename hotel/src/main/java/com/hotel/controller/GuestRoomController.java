package com.hotel.controller;

import com.hotel.entity.GuestRoom;
import com.hotel.service.ICheckInBiz;
import com.hotel.service.IGuestRoomBiz;
import com.hotel.utils.TrimUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class GuestRoomController {
    @Autowired
    private IGuestRoomBiz guestRoomBiz;
    @Autowired
    private ICheckInBiz checkInBiz;

    @RequestMapping(value="listGuestRoom.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listGuestRoom( HttpServletRequest request){

        List<GuestRoom> guestRoomList = guestRoomBiz.listAllGuestRoom();

        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("list", guestRoomList);
        return jsonObject.toString();
    }

    @RequestMapping(value="getGuestRoomByNo.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getGuestRoomByNo( @RequestParam("no") String no,
                            HttpServletRequest request){
        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(no);

        List<GuestRoom> guestRoomList = guestRoomBiz.getGuestRoomByNo(guestRoom);

        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);

        JSONObject jsonObject = new JSONObject();

        //get the latest registrationNo
        String registrationNo = checkInBiz.getLatestNoByRoomNo(no);
        if(registrationNo!=null){
            jsonObject.put("latestRegistrationNo",registrationNo.trim());
        }


        jsonObject.put("list",guestRoomList);
        return jsonObject.toString();
    }

    @RequestMapping(value="listGuestRoom_manage.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listGuestRoom( @RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request){
        int start = limit * (page - 1);
        int end = start + limit;

        List<GuestRoom> guestRoomList = guestRoomBiz.listAllGuestRoom();

        guestRoomList = TrimUtil.stringTrimGuestRoom(guestRoomList);

        // 判断是否超出数据的数量
        if (end > guestRoomList.size()) {
            end = guestRoomList.size();
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        // count 将显示为表格内元组总数
        jsonObject.put("count", guestRoomList.size());
        jsonObject.put("data", guestRoomList.subList(start, end));
        return jsonObject.toString();
    }


    @RequestMapping(value="insertGuestRoom.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertGuestRoomRank(
            @RequestParam("no") String no,
            @RequestParam("location") String location,
            @RequestParam("floor") String floor,
            @RequestParam("area") String area,
            @RequestParam("rank") String rank,
            HttpServletRequest request){

        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(no);
        guestRoom.setStatus("notInUse");
        guestRoom.setLocation(location);
        guestRoom.setFloor(floor);
        guestRoom.setArea(area);
        guestRoom.setRank(rank);

        try {
            guestRoomBiz.insertGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "insertDuplicate";
        }

        return "success";

    }

    @RequestMapping(value="updateGuestRoom.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateGuest(@RequestParam("no") String no,
                       @RequestParam("status") String status,
                       @RequestParam("location") String location,
                       @RequestParam("floor") String floor,
                       @RequestParam("area") String area,
                       @RequestParam("rank") String rank,
                       HttpServletRequest request){

        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(no);
        guestRoom.setStatus(status);
        guestRoom.setLocation(location);
        guestRoom.setFloor(floor);
        guestRoom.setArea(area);
        guestRoom.setRank(rank);

        if(guestRoomBiz.getGuestRoomByNo(guestRoom)==null){
            return "no such guestroom";
        }

        try {
            guestRoomBiz.updateGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }

    @RequestMapping(value="deleteGuestRoom.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String deleteGuestRoomRank(@RequestParam("no") String no){
        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setNo(no);

        if(guestRoomBiz.getGuestRoomByNo(guestRoom)==null){
            return "no such guestroom";
        }

        try {
            guestRoomBiz.deleteGuestRoom(guestRoom);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

//    @RequestMapping(value="queryGuestRoomRank.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//    public @ResponseBody
//    String queryGuestRoomRank(@RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("keyword") String keyword){
//
//        String keywordInSQL = "%"+keyword+"%";
//        int start = limit * (page - 1);
//        int end = start + limit;
//
//        List<GuestRoomRank> guestRoomRanks = guestRoomRankBiz.queryGuestRoomRank(keywordInSQL);
//
//        //trim
//        guestRoomRanks = TrimUtil.stringTrimGuestRoomRank(guestRoomRanks);
//
//        guestRoomRanks = TrimUtil.makeStringRed(keyword, guestRoomRanks);
//
//        // 判断是否超出数据的数量
//        if (end > guestRoomRanks.size()) {
//            end = guestRoomRanks.size();
//        }
//
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("code", 0);
//        jsonObject.put("msg", "");
//        // count 将显示为表格内元组总数
//        jsonObject.put("count", guestRoomRanks.size());
//        jsonObject.put("data", guestRoomRanks.subList(start, end));
//        return jsonObject.toString();
//    }

}
