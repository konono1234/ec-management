package com.customer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import com.customer.bean.CustomerBean;

/*
 * DBにアクセスするマッパークラスです
 */
@Transactional
@Mapper
public interface CustomerMapper {

  // @Select("select * from customer")
  public List<CustomerBean> findAll();
}
