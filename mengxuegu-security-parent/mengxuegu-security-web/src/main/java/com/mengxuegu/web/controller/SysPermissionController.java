package com.mengxuegu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller
@RequestMapping("/permission")
public class SysPermissionController {

    /*HTML页面路径前缀*/
    private static final String HTML_PREFIX = "system/permission/";

    /**
     * 菜单管理页面
     * @return
     */
    @GetMapping(value = {"/", ""}) // /permission/  /permission
    public String permission() {
        return HTML_PREFIX + "permission-list";
    }
}
