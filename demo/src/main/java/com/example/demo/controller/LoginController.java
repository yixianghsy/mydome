package com.example.demo.controller;
import com.example.demo.utils.CookieUtils;
import com.example.demo.utils.E3Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录处理
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
public class LoginController {


    private String TOKEN= "hsyhgjkfdgfkhdasghasgfvas";


    @RequestMapping(value="/api/login", method= RequestMethod.GET)
    @ResponseBody
    public E3Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        E3Result e3Result = new E3Result();
        e3Result.setMessage("登录成功");
        e3Result.setCode(0);
        e3Result.setToken(TOKEN);
        return e3Result;
    }
}

