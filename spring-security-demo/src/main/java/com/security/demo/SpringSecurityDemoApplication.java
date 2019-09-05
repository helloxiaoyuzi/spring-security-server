package com.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		//开启热部署
		System.setProperty("spring.devtools.restart.enabled", "true");
		//IDEA配置SpringBoot热启动
		/*
		第一步 File-Settings-Compiler-Build Project automatically
		第二部 ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running
		fasdfsdfasdfasfas132312
		 */
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		return System.currentTimeMillis()+"hello spring security3";
	}
}
