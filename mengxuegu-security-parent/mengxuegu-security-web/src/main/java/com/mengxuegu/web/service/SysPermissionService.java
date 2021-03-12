package com.mengxuegu.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.web.entities.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {
    /**
     * 通过用户id查询所拥有权限
     * @param userId
     * @return
     */
    List<SysPermission>  findByUserId(Long userId);
}
