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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户 + 管理员登录
     * @param username  账号名
     * @param password  密码
     * @param httpSession
     * @return  登录成功的用户
     */
    @PostMapping(path = "/users/login")
    public User doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam("authority") Integer authority, HttpSession httpSession, HttpServletResponse response) throws IOException {
        System.out.println("User---username："+ username + " password：" + password + "login");
        Msg msg = new Msg(200,"登录成功");
        User user = userService.doLogin(username,password,authority);
        if (user != null) {
            httpSession.setAttribute("USER_SESSION",user);
            response.setStatus(200);
        } else {
            response.sendError(500,"can't login");
        }
        return user;
    }

    /**
     * 用户注册
     * @param username  账户名
     * @param password  密码
     * @param phone  手机号
     * @param httpSession
     * @return  注册成功的用户
     */
    @PutMapping(path = "/users/register")
    public User doRegister(@RequestParam("username") String username,@RequestParam("password") String password,
                          @RequestParam("phone") String phone,@RequestParam("authority") Integer authority,
                          HttpSession httpSession,HttpServletResponse response) throws IOException {
        System.out.println("Customer---username："+ username + " password：" + password + "register");
        Msg msg = new Msg(200,"注册成功");
        User user = userService.doRegister(username, password, phone,authority);
        if (user != null) {
            httpSession.setAttribute("USER_SESSION",user);
            response.setStatus(200);;
        } else {
            response.sendError(500,"can't register");
        }
        return user;
    }
}
