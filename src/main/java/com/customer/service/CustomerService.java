package com.customer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.bean.CustomerBean;
import com.customer.mapper.CustomerMapper;

/*
 * 顧客情報を取り出すサービスクラスです
 */
@Service
public class CustomerService {

  @Autowired
  CustomerMapper customerMapper;

  // selectAll

  public List<CustomerBean> findAllIndex() {
    List<CustomerBean> customerList = customerMapper.findAll();
    return customerList;
  }
}
