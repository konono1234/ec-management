package com.customer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;

/*
 * DBにアクセスするマッパークラスです
 */
@Transactional
@Mapper
public interface CustomerMapper {

  // select *
  public List<CustomerBean> selectAllCustomer();

  // create
  public void insertCustomer(CustomerForm customerForm);

  // detail
  public List<CustomerBean> selectByNumber(Integer cust_no);

  // delete
  public void deleteByNumber(Integer cust_no);

  // edit
  public void updateCustomer(CustomerForm customerForm);

  // editForm
  public CustomerForm createCustomerForm(Integer cust_no);

  // search
  public List<CustomerBean> selectByKeyword(CustomerForm customerForm);

  // haitaseigyo
  public String updateTime(String cust_no);
}
