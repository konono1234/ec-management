package com.customer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;
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

    List<CustomerBean> customerList = customerService.findAllCustomer();
    model.addAttribute("customerList", customerList);
    model.addAttribute("customerForm", new CustomerForm());
    return "customer/index";
  }

  @RequestMapping(value = "/customer/create")
  public String create(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
    return "customer/create";
  }

  @RequestMapping(value = "/customer/save-create")
  public String saveForm(@ModelAttribute @Validated CustomerForm customerForm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "customer/create";
    } else {
      try {
        customerService.createCustomer(customerForm);
        model.addAttribute("customerForm", customerForm);
        return "customer/save";

      } catch (Exception e) {
        model.addAttribute("errorMessage", "顧客番号が重複しています。別の番号を指定してください");
        return "customer/create";
      }

    }
  }

  @RequestMapping(value = "/customer/{cust_no}", method = RequestMethod.GET)
  public ModelAndView detailForm(@PathVariable Integer cust_no) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("customer/detail");

    List<CustomerBean> customerList = customerService.findByNumber(cust_no);
    mv.addObject("customerList", customerList);

    return mv;
  }


}
