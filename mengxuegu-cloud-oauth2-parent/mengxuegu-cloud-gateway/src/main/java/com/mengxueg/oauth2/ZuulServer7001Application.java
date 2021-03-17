package com.mengxueg.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy //开启zuul的功能
@EnableEurekaClient
@SpringBootApplication
public class ZuulServer7001Application {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServer7001Application.class, args);
	}

}
