package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.order.bean.OrderForm;
import com.order.service.OrderService;

/*
 * Serviceを起動したりViewにデータを渡す注文情報クラスです
 */

@Controller
public class OrderController {
  @ModelAttribute
  OrderForm setupForm() {
    return new OrderForm();
  }

  @Autowired
  OrderService orderService;
}
