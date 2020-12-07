package com.customer.auth;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.customer.service.UserService;

//認証画面を制御するコントローラークラス
@Controller
public class AuthController {
	
	final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	UserService userService;
	
	/**
	 * ログイン画面を表示する [/login]を拾ってauthフォルダのlogin.htmlに飛ばす
	 * 力されたuseridでデータベースに検索をしつつidとpasswordが一緒かどうか調べる
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/login")
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		if(userName.equals("anonymousUser")) {
			userName = "true";
			model.addAttribute("userName", userName);
			return "auth/login";
		} else {
			model.addAttribute("userName", userName);
			return "auth/login";
		}
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
