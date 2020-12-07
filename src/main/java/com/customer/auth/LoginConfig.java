package com.customer.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class LoginConfig {

	public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/login").setViewName("login");
	}

}
