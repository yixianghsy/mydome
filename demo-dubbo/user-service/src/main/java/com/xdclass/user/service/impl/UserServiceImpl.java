package com.xdclass.user.service.impl;

import com.xdclass.user.service.UserService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        return "hello";
    }
}
