package com.mengxuegu.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengxuegu.web.entities.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper   extends BaseMapper<SysPermission> {
    /**
     * 根据用户ID获取拥有资源权限
     * @param userId 用户id
     * @return
     */
    List<SysPermission> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID获取拥有资源权限
     * @param roleId 角色ID
     * @return 资源权限集合
     */
    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);

}
