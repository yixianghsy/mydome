package com.mengxuegu.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties( prefix = "mengxuegu.security") 绑定 application.yml 配置文件中
 * 以 mengxuegu.security 前缀的数据
 */
@Component // 不要少了
@ConfigurationProperties( prefix = "mengxuegu.security")
public class SecurityProperties {
    // 将application.yml 中的 mengxuegu.security.authentication 下面的值绑定到此对象中
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}