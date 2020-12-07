package com.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.bean.LoginUser;

//SpringFrameWorkのログイン機能を実装しつつLoginUserDaoクラスを起動
@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	LoginUserDao loginUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		LoginUser user = loginUserDao.findUser(userName);
		System.out.println("LOGINUSER INSTANCE");

		if (user == null) {
			System.out.println(userName+"は見つからない");
			throw new UsernameNotFoundException("userID:" + userName + "は見つかりません");
		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("userinfo");
		grantList.add(authority);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		UserDetails userDetails =
				(UserDetails) new User(user.getUser_id(), encoder.encode(user.getPassword()), grantList);

		return userDetails;
	}

	
}
