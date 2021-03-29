package com.example.demo.controller;

import com.example.demo.utils.E3Result;
import com.sun.deploy.net.HttpResponse;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class RegisterController {
    @RequestMapping(value="/user/register", method= RequestMethod.GET)
    @ResponseBody
    public E3Result  register(@RequestParam("username") String username,@RequestParam("password") String password) {
//        return  "null";
        System.out.println("username:"+username+"password:"+password);
         E3Result e3Result = new E3Result();
        e3Result.setStatus(200);
        e3Result.setMsg("注册成功");
        return e3Result;
    }
    @ResponseBody
    @RequestMapping(value="/hello/{id}/{name}",method= RequestMethod.GET)
    public String sayHello(@PathVariable("id") Integer id, @PathVariable("name") String name){
        return "id:"+id+" name:"+name;
    }

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String sayHello1(@RequestParam("userId") Integer id,@RequestParam("password") Integer password){
        return "id:"+id +"password:"+password;
    }
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String sayHello1(HttpServletRequest request,
                            HttpServletResponse response, Model model){

        request.getMethod();
        request.getMethod();
        System.out.println(model.getAttribute("username"));

        return "id:";
    }
}
