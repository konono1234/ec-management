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

  // delete
  public void deleteCustomer(Integer cust_no) {
    customerMapper.deleteByNumber(cust_no);
  }

  // edit
  public void editCustomer(CustomerForm customerForm) {
    customerMapper.updateCustomer(customerForm);
  }

  // editForm
  public CustomerForm editCustomerForm(Integer cust_no) {
    CustomerForm customerForm = customerMapper.createCustomerForm(cust_no);
    return customerForm;
  }

  // search
  public List<CustomerBean> searchByKeyword(CustomerForm customerForm) {
    List<CustomerBean> customerList = customerMapper.selectByKeyword(customerForm);
    return customerList;
  }

  // checkbyNumber
  public String checkByTime(String cust_no) {
    String update_date = customerMapper.updateTime(cust_no);
    return update_date;
  }
}
