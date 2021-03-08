package com.mengxuegu.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * alt+/ 导包
 * ctrl+o 覆盖
 */
@EnableWebSecurity // 开启springsecurity过滤链 filter
public class SpringSecurityConfig   extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文+随机盐值》加密存储
        return new BCryptPasswordEncoder();
    }
    /**
     * 资源权限配置
     * 1. 被拦截的资源
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login/page")
                // 登录表单提交处理url, 默认是/login
                .loginProcessingUrl("/login/form")
                //默认的是 username
                .usernameParameter("name")
                // 默认的是 password
                .passwordParameter("pwd")
                .and()
                //请求认证
                .authorizeRequests()
                .antMatchers("/login/page").permitAll()
                .anyRequest().authenticated();// 所有进入应用的HTTP请求都要进行认证
    }

    /**
     * 释放静态资源
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/dist/**", "/modules/**", "/plugins/**");
    }

    /**
     *  认证管理器：
     *  1. 认证信息（用户名，密码）
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       String password =  passwordEncoder().encode("1234");
       auth.inMemoryAuthentication().withUser("mengxuegu").password(password).authorities("ADMIN");
    }
}

