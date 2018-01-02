package com.demo.controller;

import com.demo.pojo.TbUser;
import com.demo.service.TbUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TbUserController {

    @Value("${TEST_DATA}")
    private String data;

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("/welcome/{phoneNum}")
    public String toWelcomePage(@PathVariable String phoneNum, Model model){

        System.out.print("Controller:"+data);
        System.out.print(phoneNum);
        TbUser user = tbUserService.getUserByPhoneNum(phoneNum);
        model.addAttribute("user",user);
        return "welcome";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    public String toWelcomePage(@Param("name") String name, TbUser user, HttpServletRequest request){
        System.out.print(user.getName()+"!!!!");
        System.out.print("Controller:"+name);
        String reqd = request.getParameter("name");
        System.out.print("qeqd:"+name);
        return "welcome";
    }
}
