package com.customer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.customer.bean.CustomerBean;

@Mapper
public interface CustomerMapper {

  @Select("select * from customer")
  List<CustomerBean> findAll();
}
