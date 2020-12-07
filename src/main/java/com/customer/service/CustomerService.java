package com.customer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;
import com.customer.mapper.CustomerMapper;

/*
 * 顧客情報を取り出すサービスクラスです
 */
@Service
public class CustomerService {

  @Autowired
  CustomerMapper customerMapper;

  // selectAll

  public List<CustomerBean> findAllCustomer() {
    List<CustomerBean> customerList = customerMapper.selectAllCustomer();
    return customerList;
  }

  // insert
  public void createCustomer(CustomerForm customerForm) {
    customerMapper.insertCustomer(customerForm);
  }

  // detail
  public List<CustomerBean> findByNumber(Integer cust_no) {
    List<CustomerBean> customerList = customerMapper.selectByNumber(cust_no);
    return customerList;
  }
}
