package com.customer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.customer.bean.CustomerBean;
import com.customer.service.CustomerService;

/*
 * Serviceを起動したりViewにデータを渡す顧客情報コントローラークラスです
 */

@Controller
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @RequestMapping(value = "/customer")
  public String initIndex(Model model) {

    List<CustomerBean> customerList = customerService.findAllIndex();
    model.addAttribute("customerList", customerList);
    if (customerList == null) {
      System.out.print("空っぽ");
    } else {
      System.out.print("入ってる");
    }
    return "customer/index";
  }
}
