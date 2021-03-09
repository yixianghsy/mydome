package com.mengxuegu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller
public class CustomLoginController {

    @RequestMapping("/login/page")
    public String toLogin() {
        return "login"; // classpath: /templates/login.html
    }

}
