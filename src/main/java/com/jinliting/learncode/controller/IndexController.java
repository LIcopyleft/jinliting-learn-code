package com.jinliting.learncode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        String format = sdf.format(new Date());

        String substring = format.substring(8);

        if (!"jinliting".equals(username) || !substring.equals(password)) {
            response.getWriter().println("用户名或密码错误 , 温馨提示, 密码为当前时间小时+分钟,例如上午八点整,密码为0800");
            return;
        }


        Cookie cookie = new Cookie("jin_li_ting_cookie", UUID.randomUUID().toString());
        cookie.setMaxAge(1000);
        response.addCookie(cookie);
        cookie.setDomain("localhost");

        response.getWriter().println("登陆成功");

        //    return "我是后台 , 我接受到你的参数" + username + "\t" + password;
    }
}
