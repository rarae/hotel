package com.hotel.controller;

import com.hotel.entity.Admin;
import com.hotel.service.IAdminBiz;
import com.hotel.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private IAdminBiz adminBiz;


    @RequestMapping(value="login.do", method = RequestMethod.POST)
    public @ResponseBody
    String adminLogin(@RequestParam("username") String username,
                      @RequestParam("password") String passwrod,
                      HttpServletRequest request){

        Admin admin = adminBiz.getAdminFromUsername(username);

        if(admin==null){
            return "noSuchAdmin";
        }

        if(!admin.getPassword().equals(MD5Util.getMD5(passwrod))){
            return "passwordInvalid";
        }

        request.getSession().setAttribute("username", admin.getUsername());
        return "success";

    }

    @RequestMapping(value="getAdminType.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getAdminType(HttpServletRequest request){
        if(request.getSession().getAttribute("username")!=null){
            String admin = request.getSession().getAttribute("username").toString().trim();

            Admin admin1 = adminBiz.getAdminFromUsername(admin);
            if(admin==null){
                return "noSuchAdmin";
            }
            return admin1.getType().trim();
        }
        return "recep";
    }
}
