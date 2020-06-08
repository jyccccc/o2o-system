package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.bean.Customer;
import com.jyc.o2o_demo.service.UserService;
import com.jyc.o2o_demo.utils.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/users/login")
    public Msg doLogin(String username, String password, HttpSession httpSession) {
        System.out.println("Customer---username："+ username + " password：" + password + "login");
        Msg msg = new Msg(200,"登录成功");
        Customer customer = userService.doLogin(username,password);
        if (customer != null) {
            httpSession.setAttribute("USER_SESSION",customer);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("Customer",customer);
            msg.setData(map);
        } else {
            msg.setCM(200, "登录失败，请重新输入");
        }
        return msg;
    }

    @PutMapping(path = "/users/register")
    public Msg doRegister(String username, String password, String phone, HttpSession httpSession) {
        System.out.println("Customer---username："+ username + " password：" + password + "register");
        Msg msg = new Msg(200,"注册成功");
        Customer customer = userService.doRegister(username, password, phone);
        if (customer != null) {
            httpSession.setAttribute("USER_SESSION",customer);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("Customer",customer);
            msg.setData(map);
        } else {
            msg.setCM(200, "注册失败");
        }
        return msg;
    }
}
