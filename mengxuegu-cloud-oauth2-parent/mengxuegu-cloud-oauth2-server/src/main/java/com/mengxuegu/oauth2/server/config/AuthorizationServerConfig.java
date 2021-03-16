package com.mengxuegu.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * 认证服务器配置类
 */
@Configuration
@EnableAuthorizationServer // 开启了认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired // 刷新令牌
    private UserDetailsService customUserDetailsService;

    @Autowired // token管理方式，在TokenConfig类中已对添加到容器中了
    private TokenStore tokenStore;


    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
/*        // 内存方式管理客户端信息
        clients.inMemory()
                *//**
                 * 允许访问此认证服务器的客户端id , 如：PC、APP、小程序各不同的的客户端id。
                 * 客户端id
                 *//*
                .withClient("mengxuegu-pc")
                *//**
                 * 客户端密码，要加密存储，不然获取不到令牌一直要求登录,, 而且一定不能被泄露。
                 *//*
                .secret(passwordEncoder.encode("mengxuegu-secret"))
                *//**
                 * 资源id，针对的是微服务名称，商品管理
                 *//*
                .resourceIds("product-server")
                *//**
                 * 授权类型, 可同时支持多种授权类型：
                 * 可配置："authorization_code", "password", "implicit","client_credentials","refresh_token"
                 *//*
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                *//**
                 * 授权范围标识，如指定微服务名称，则只能访问指定的微服务(all只是标识，不是说所有资源)
                 *//*
                .scopes("all")
                *//**
                 * false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码
                 *//*
                .autoApprove(false)
                *//**
                 * 客户端回调地址
                 * 当获取授权码后，认证服务器会重定向到这个URI，并且带着一个授权码 code 响应回来。
                 *//*
                .redirectUris("http://www.mengxuegu.com/");*/
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 授权码管理策略
     * @return
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
}
