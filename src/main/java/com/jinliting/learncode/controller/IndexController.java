package com.jinliting.learncode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tengchao.li
 * @description
 * @date 2023/9/13
 */

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/test")
    public String test(String username, String password, HttpServletResponse response) {


        return "我是后台 , 我接受到你的参数" + username + "\t" + password;
    }

    @RequestMapping("/login")
    public void login(String username, String password, HttpServletResponse response) throws Exception {

        Cookie cookie = new Cookie("jin_li_ting_cookie", UUID.randomUUID().toString());
        cookie.setMaxAge(1000);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.getWriter().println("登陆成功");

    //    return "我是后台 , 我接受到你的参数" + username + "\t" + password;
    }
}
