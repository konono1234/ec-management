package com.customer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;

@Controller
public class CustomerController {

	 @RequestMapping(value = "/customer")
	  public String initIndex(Model model) {

	    //List<CustomerBean> list = customerService.selectIndex();
	    //model.addAttribute("list", list);
	    //model.addAttribute("customerForm", new CustomerForm());

	    return "customer/menu";
	  }

}
