package com.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.customer.bean.LoginUser;

@Mapper
public interface LoginMapper {

	@Select("SELECT * FROM userinfo WHERE user_id = #{user_id}")
	  LoginUser findUser(String user_id);

}
