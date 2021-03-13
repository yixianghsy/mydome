package com.mengxuegu.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengxuegu.base.result.MengxueguResult;
import com.mengxuegu.web.entities.SysRole;
import com.mengxuegu.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    private static final String HTML_PREFIX = "system/role/";
    @PreAuthorize("hasAuthority('sys:role')")
    @GetMapping(value = {"/", ""}) // /role/  /role
    public String role() {
        return HTML_PREFIX + "role-list";
    }



    /**
     * 分页:角色列表数据
     * @param page 分页
     * @param sysRole
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @PostMapping("/page") // /role/page
    @ResponseBody
    public MengxueguResult page(Page<SysRole> page, SysRole sysRole) {
        return MengxueguResult.ok( sysRoleService.selectPage(page, sysRole) );
    }

    /**
     * 跳转新增或修改页面
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:role:add', 'sys:role:edit')")
    @GetMapping(value={"/form", "/form/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        // 通过角色id查询对应的角色信息和权限信息
        SysRole role = sysRoleService.findById(id);
        model.addAttribute("role", role);

        return HTML_PREFIX + "role-form";
    }

    /**
     * 提交新增或者修改的数据
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:role:add', 'sys:role:edit')")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "") //  /role
    public String saveOrUpdate(SysRole sysRole) {
//        System.out.println("sysRole: " + sysRole);
        sysRoleService.saveOrUpdate(sysRole);
        return "redirect:/role";
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/{id}") // /role/{id}
    @ResponseBody
    public MengxueguResult deleteById(@PathVariable("id") Long id) {
        sysRoleService.deleteById(id);
        return MengxueguResult.ok();
    }


}
