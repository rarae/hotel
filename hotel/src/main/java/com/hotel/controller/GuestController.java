package com.hotel.controller;

import com.hotel.entity.Admin;
import com.hotel.entity.Guest;
import com.hotel.service.IGuestBiz;
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
public class GuestController {
    @Autowired
    private IGuestBiz guestBiz;

    @RequestMapping(value="insertGuest.do", method = RequestMethod.POST)
    public @ResponseBody
    String insertGuest(@RequestParam("IDType") String IDType,
                      @RequestParam("ID") String ID,
                      @RequestParam("name") String name,
                      @RequestParam("sex") String sex,
                      @RequestParam("age") String age,
                      @RequestParam("phone") String phone,
                      @RequestParam("address") String address,
                      @RequestParam("note") String note,
                      HttpServletRequest request){

        Guest guest = new Guest();
        guest.setIDType(IDType);
        guest.setID(ID);
        guest.setName(name);
        guest.setSex(sex);
        guest.setAge(age);
        guest.setPhone(phone);
        guest.setAddress(address);
        guest.setNote(note);

        try {
            guestBiz.insertGuest(guest);
        }
        catch (Exception e){
            e.printStackTrace();
            return "insertDuplicate";
        }

        return "success";

    }

    @RequestMapping(value="listGuest.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String listGuest(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request){
        int start = limit * (page - 1);
        int end = start + limit;

        List<Guest> guests = guestBiz.listAllGuest();

        //trim
        guests = TrimUtil.stringTrimGuest(guests);

        // 判断是否超出数据的数量
        if (end > guests.size()) {
            end = guests.size();
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        // count 将显示为表格内元组总数
        jsonObject.put("count", guests.size());
        jsonObject.put("data", guests.subList(start, end));
        return jsonObject.toString();
    }


    @RequestMapping(value="updateGuest.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateGuest(@RequestParam("IDType") String IDType,
                       @RequestParam("ID") String ID,
                       @RequestParam("name") String name,
                       @RequestParam("age") String age,
                       @RequestParam("phone") String phone,
                       @RequestParam("address") String address,
                       @RequestParam("note") String note, HttpServletRequest request){
        Guest guest = new Guest();
        guest.setIDType(IDType);
        guest.setID(ID);
        guest.setName(name);
        guest.setAge(age);
        guest.setPhone(phone);
        guest.setAddress(address);
        guest.setNote(note);

        if(guestBiz.getGuestById(guest)==null){
            return "no such guest";
        }

        try {
            guestBiz.updateGuest(guest);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }

    @RequestMapping(value="deleteGuest.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateGuest(@RequestParam("IDType") String IDType,
                       @RequestParam("ID") String ID
                      ){
        Guest guest = new Guest();
        guest.setIDType(IDType);
        guest.setID(ID);

        if(guestBiz.getGuestById(guest)==null){
            return "no such guest";
        }

        try {
            guestBiz.deleteGuest(guest);
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value="queryGuest.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateGuest(@RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("keyword") String keyword){

        String keywordInSQL = "%"+keyword+"%";
        int start = limit * (page - 1);
        int end = start + limit;

        List<Guest> guests = guestBiz.queryGuest(keywordInSQL);

        //trim
        guests = TrimUtil.stringTrimGuest(guests);

        guests = TrimUtil.makeStringRedGuest(keyword, guests);

        // 判断是否超出数据的数量
        if (end > guests.size()) {
            end = guests.size();
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        // count 将显示为表格内元组总数
        jsonObject.put("count", guests.size());
        jsonObject.put("data", guests.subList(start, end));
        return jsonObject.toString();
    }

}
