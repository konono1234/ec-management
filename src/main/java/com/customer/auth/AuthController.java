package com.customer.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
	
	final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	/**
	 * ログイン画面を表示する
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "auth/login";
	}

	/**
	 * ログインエラー画面を表示する
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "auth/login";
	}

}
