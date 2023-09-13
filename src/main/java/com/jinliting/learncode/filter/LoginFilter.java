package com.jinliting.learncode.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tengchao.li
 * @description
 * @date 2023/9/13
 */
@Configuration
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    static Set<String> ignorUriSet = new HashSet<>();

    static {
        ignorUriSet.add("index/login");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;

        String requestURI = servletRequest1.getRequestURI();

        boolean match = ignorUriSet.stream().anyMatch(ignoruri -> requestURI.endsWith(ignoruri));
        if (match) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Cookie[] cookies = servletRequest1.getCookies();
        if (cookies == null) {


            extracted(servletResponse);
            return;
        }
        Cookie cookieS = Arrays.stream(cookies).filter(cookie -> {
            if ("jin_li_ting_cookie".equals(cookie.getName())) {
                return true;
            }
            return false;
        }).filter(Objects::nonNull).findFirst().get();

        if (cookieS == null) {
            //禁止访问

            extracted(servletResponse);

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    private static void extracted(ServletResponse servletResponse) throws IOException {

        ((HttpServletResponse) servletResponse).setHeader("Content-Type", "application/json; charset=UTF-8");
        servletResponse.getWriter().print("没有登陆禁止访问");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
