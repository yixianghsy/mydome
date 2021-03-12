package com.mengxuegu.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.mapper.SysUserMapper;
import com.mengxuegu.web.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * serviceImpl<M extends BaseMapper<T>, T> 是对 IService接口中方法的实现
 * 第1个泛型 M 指定继承了 BaseMapper接口的子接口
 * 第2个泛型 T 指定实体类
 */
@Service
public class SysUserServiceImpl    extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findByUsername(String username) {
        if (StringUtils.isEmpty(username)){
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        // baseMapper 对应的是就是 SysUserMapper
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public SysUser findByMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)){
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",mobile);
        return baseMapper.selectOne(queryWrapper);
    }
}


