package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysPermissionService;
import com.mengxuegu.web.service.SysUserService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过手机号获取用户信息和权限资源
 */
@Component("mobileUserDetailsService") // 一定不要少了
public class MobileUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysPermissionService sysPermissionService;
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        logger.info("请求的手机号是：" + mobile);
        // 1. 通过手机号查询用户信息
        SysUser sysUser = sysUserService.findByMobile(mobile);
        if(sysUser == null) {
            throw new UsernameNotFoundException("该手机号未注册");
        }
        // 2. 如果有用户信息，则再获取权限资源
        List<SysPermission> permissions = sysPermissionService.findByUserId(sysUser.getId());
        if (CollectionUtils.isEmpty(permissions)){
            return  sysUser;
        }
        // 在左侧菜单 动态渲染会使用，目前先把它都传入
        sysUser.setPermissions(permissions);
        // 3. 封装用户信息
        ArrayList<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysPermission sp : permissions){
            // 权限标识
            String code = sp.getCode();
            authorities.add(new SimpleGrantedAuthority(code));

        }
        sysUser.setAuthorities(authorities);
        return sysUser;
    }
}
