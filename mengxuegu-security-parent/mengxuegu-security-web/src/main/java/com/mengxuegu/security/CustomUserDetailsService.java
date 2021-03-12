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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 查询数据库中的用户信息
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("请求认证的用户名: " + username);

        // 1. 通过请求的用户名去数据库中查询用户信息

        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null){
            throw  new UsernameNotFoundException("用户名或密码错误");
        }
        // 2. 查询该用户有哪一些权限
        List<SysPermission> permissions = sysPermissionService.findByUserId(sysUser.getId());
        if (CollectionUtils.isEmpty(permissions)){
            return  sysUser;
        }
        //在左侧菜单 动态渲染会使用，目前先把它都传入
        sysUser.setPermissions(permissions);

        // 3. 封装用户信息和权限信息
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysPermission sp : permissions) {
            // 权限标识
            String code = sp.getCode();
            authorities.add(new SimpleGrantedAuthority(code));
        }
        sysUser.setAuthorities(authorities);

        // 4. 交给springsecurity自动进行身份认证
        return sysUser;
    }

}
