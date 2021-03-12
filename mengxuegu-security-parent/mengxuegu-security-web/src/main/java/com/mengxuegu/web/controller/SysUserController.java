package com.mengxuegu.web.controller;

import com.mengxuegu.base.result.MengxueguResult;
import org.assertj.core.util.Lists;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户管理
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final String HTML_PREFIX = "system/user/";


    @PreAuthorize("hasAuthority('sys:user')")
    @GetMapping(value = {"/", ""}) // /user/  /user
    public String user() {
        return HTML_PREFIX + "user-list";
    }

    /**
     * 跳转到新增或者修改页面
     * @return
     */
    // 有 'sys:user:add' 或 'sys:user:edit'权限 的用户可以访问
    @PreAuthorize("hasAnyAuthority('sys:user:add', 'sys:user:edit')")
    @GetMapping(value={"/form"}) // /user/form
    public String form() {
        return HTML_PREFIX + "user-form";
    }

    // 返回值的code等于200，则调用成功有权限 ，否则把403
    @PostAuthorize("returnObject.code == 200")
    @RequestMapping("/{id}")  // /user/{id}
    @ResponseBody
    public MengxueguResult deleteById(@PathVariable Long id) {

        if(id < 0) {
            return MengxueguResult.build(500, "id不能小于0", id);
        }

        return MengxueguResult.ok();
    }

    // 过滤请求参数：filterTarget 指定哪个参数，filterObject是集合中的每个元素，
    // 如果value表达式为true的数据则不会被过滤，否则 就过滤掉
    @PreFilter(filterTarget = "ids", value = "filterObject > 0")
    @RequestMapping("/batch/{ids}") // /user/batch/-1,0,1,2
    @ResponseBody
    public MengxueguResult deleteByIds(@PathVariable List<Long> ids) {
        return MengxueguResult.ok(ids);
    }

    // 过滤返回值：filterObject是返回值集合中的每一个元素，当表达式为true则对应元素会返回
    @PostFilter("filterObject != authentication.principal.username")
    @RequestMapping("/list")
    @ResponseBody
    public List<String> page() {
        List<String> userList = Lists.newArrayList("meng", "xue", "gu");
        return userList;
    }

}
