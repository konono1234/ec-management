package com.customer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.customer.bean.LoginUser;

@Mapper
public interface LoginMapper {

	  LoginUser findUser(String user_id);

}
