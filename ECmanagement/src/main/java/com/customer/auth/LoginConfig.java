package com.customer.auth;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

//パスを受けとりログイン画面に遷移
public class LoginConfig {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
}
