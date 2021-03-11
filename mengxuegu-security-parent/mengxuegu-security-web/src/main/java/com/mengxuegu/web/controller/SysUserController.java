package com.mengxuegu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final String HTML_PREFIX = "system/user/";

    /**
     * 前往用户列表页面
     * @return
     */
    @GetMapping(value = {"/", ""})
    public String user() {
        return HTML_PREFIX + "user-list";
    }
}
