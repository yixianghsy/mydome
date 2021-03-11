package com.mengxuegu.security.authorize;

import com.mengxuegu.security.properties.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 身份认证相关的授权配置
 */
@Component
@Order(Integer.MAX_VALUE) // 值越小加载越优先，值越大加载越靠后
public class CustomAuthorizeConfigurerProvider implements AuthorizeConfigurerProvider {

    private SecurityProperties securityProperties;
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(securityProperties.getAuthentication().getLoginPage(),
                securityProperties.getAuthentication().getImageCodeUrl(),
                securityProperties.getAuthentication().getMobilePage(),
                securityProperties.getAuthentication().getMobileCodeUrl()
        ).permitAll();
    }


}
