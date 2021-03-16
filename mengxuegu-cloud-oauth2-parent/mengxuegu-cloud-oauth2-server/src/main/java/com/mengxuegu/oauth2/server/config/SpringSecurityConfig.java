package com.mengxuegu.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 * 模拟用户输入用户名和密码进行认证
 * 指定要进行认证用户的用户名和密码，这个用户名和密码是资源所有者的。
 * 和上面指定的客户端id和密码是不一样的，客户端的id和密码是应用系统的标识，每个应用系统就对应一个客户端
 * ip和密码。
 * 而这里指定的用户名和密码是客户的，就是每个应用系统的用户，即资源所有者。
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder.encode("1234"))
//                .authorities("product");
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * password 模式需要此bean
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
