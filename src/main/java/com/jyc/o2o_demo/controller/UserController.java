package com.jyc.o2o_demo.controller;

import com.jyc.o2o_demo.bean.Admin;
import com.jyc.o2o_demo.bean.Msg;
import com.jyc.o2o_demo.bean.Customer;
import com.jyc.o2o_demo.bean.User;
import com.jyc.o2o_demo.constant.UserConstants;
import com.jyc.o2o_demo.service.UserService;
import com.jyc.o2o_demo.utils.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户 + 管理员登录
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
    @PostMapping(path = "/users/login")
    public Msg doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                       @RequestParam("authority") Integer authority, HttpSession httpSession) {
        System.out.println("User---username："+ username + " password：" + password + "login");
        Msg msg = new Msg(200,"登录成功");
        User user = userService.doLogin(username,password,authority);
        if (user != null) {
            httpSession.setAttribute("USER_SESSION",user);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("User",user);
            msg.setData(map);
        } else {
            msg.setCM(200, "登录失败，请重新输入");
        }
        return msg;
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param phone
     * @param httpSession
     * @return
     */
    @PutMapping(path = "/users/register")
    public Msg doRegister(@RequestParam("username") String username,@RequestParam("password") String password,
                          @RequestParam("phone") String phone,@RequestParam("authority") Integer authority,HttpSession httpSession) {
        System.out.println("Customer---username："+ username + " password：" + password + "register");
        Msg msg = new Msg(200,"注册成功");
        User user = userService.doRegister(username, password, phone,authority);
        if (user != null) {
            httpSession.setAttribute("USER_SESSION",user);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("User",user);
            msg.setData(map);
        } else {
            msg.setCM(200, "注册失败");
        }
        return msg;
    }
}
