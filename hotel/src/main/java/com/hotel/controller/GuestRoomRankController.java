package com.hotel.controller;

import com.hotel.entity.Admin;
import com.hotel.entity.Guest;
import com.hotel.entity.GuestRoomRank;
import com.hotel.service.IGuestBiz;
import com.hotel.service.IGuestRoomRankBiz;
import com.hotel.utils.MD5Util;
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
public class GuestRoomRankController {
    @Autowired
    private IGuestRoomRankBiz guestRoomRankBiz;

    @RequestMapping(value="insertGuestRoomRank.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertGuestRoomRank(
                      @RequestParam("rank") String rank,
                      @RequestParam("numOfPeople") String numOfPeople,
                      @RequestParam("price") String price,
                      @RequestParam("facility") String facility,
                      HttpServletRequest request){

        GuestRoomRank guestRoomRank = new GuestRoomRank();
        guestRoomRank.setRank(rank);
        guestRoomRank.setNumOfPeople(numOfPeople);
        guestRoomRank.setPrice(price);
        guestRoomRank.setFacility(facility);

        try {
            guestRoomRankBiz.insertGuestRoomRank(guestRoomRank);
        }
        catch (Exception e){
            e.printStackTrace();
            return "insertDuplicate";
        }

        return "success";

    }

    @RequestMapping(value="listGuestRoomRank.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listGuestRoomRank(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request){
        int start = limit * (page - 1);
        int end = start + limit;

        List<GuestRoomRank> guestRoomRanks = guestRoomRankBiz.listAllGuestRoomRank();

        //trim
        guestRoomRanks = TrimUtil.stringTrimGuestRoomRank(guestRoomRanks);

        // 判断是否超出数据的数量
        if (end > guestRoomRanks.size()) {
            end = guestRoomRanks.size();
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        // count 将显示为表格内元组总数
        jsonObject.put("count", guestRoomRanks.size());
        jsonObject.put("data", guestRoomRanks.subList(start, end));
        return jsonObject.toString();
    }


    @RequestMapping(value="updateGuestRoomRank.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateGuest(@RequestParam("rank") String rank,
                       @RequestParam("numOfPeople") String numOfPeople,
                       @RequestParam("price") String price,
                       @RequestParam("facility") String facility,
                       HttpServletRequest request){

        GuestRoomRank guestRoomRank = new GuestRoomRank();
        guestRoomRank.setRank(rank);
        guestRoomRank.setNumOfPeople(numOfPeople);
        guestRoomRank.setPrice(price);
        guestRoomRank.setFacility(facility);

        if(guestRoomRankBiz.getGuestRoomRankByRank(guestRoomRank)==null){
            return "no such guest";
        }

        try {
            guestRoomRankBiz.updateGuestRoomRank(guestRoomRank);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }

    @RequestMapping(value="deleteGuestRoomRank.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String deleteGuestRoomRank(@RequestParam("rank") String rank){
        GuestRoomRank guestRoomRank = new GuestRoomRank();
        guestRoomRank.setRank(rank);

        if(guestRoomRankBiz.getGuestRoomRankByRank(guestRoomRank)==null){
            return "no such guest";
        }

        try {
            guestRoomRankBiz.deleteGuestRoomRank(guestRoomRank);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value="queryGuestRoomRank.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String queryGuestRoomRank(@RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("keyword") String keyword){

        String keywordInSQL = "%"+keyword+"%";
        int start = limit * (page - 1);
        int end = start + limit;

        List<GuestRoomRank> guestRoomRanks = guestRoomRankBiz.queryGuestRoomRank(keywordInSQL);

        //trim
        guestRoomRanks = TrimUtil.stringTrimGuestRoomRank(guestRoomRanks);

        guestRoomRanks = TrimUtil.makeStringRed(keyword, guestRoomRanks);

        // 判断是否超出数据的数量
        if (end > guestRoomRanks.size()) {
            end = guestRoomRanks.size();
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        // count 将显示为表格内元组总数
        jsonObject.put("count", guestRoomRanks.size());
        jsonObject.put("data", guestRoomRanks.subList(start, end));
        return jsonObject.toString();
    }

}
