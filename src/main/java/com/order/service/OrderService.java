package com.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.bean.OrderBean;
import com.order.bean.OrderForm;
import com.order.mapper.OrderMapper;

@Service
public class OrderService {

  @Autowired
  OrderMapper orderMapper;

  // selectAll
  public List<OrderBean> findAllOrder() {
    List<OrderBean> orderList = orderMapper.selectAllOrder();
    return orderList;
  }

  // insert
  public void createOrder(OrderForm orderForm) {
    orderMapper.insertOrder(orderForm);
  }

  // detail
  public List<OrderBean> findByNumber(Integer order_no) {
    List<OrderBean> orderList = orderMapper.selectByNumber(order_no);
    return orderList;
  }

  // delete
  public void deleteOrder(Integer order_no) {
    orderMapper.deleteByNumber(order_no);
  }

  // edit
  public void editOrder(OrderForm orderForm) {
    orderMapper.updateOrder(orderForm);
  }

  // editForm
  public OrderForm editOrderForm(Integer order_no) {
    OrderForm orderForm = orderMapper.createOrderForm(order_no);
    return orderForm;
  }

  // search
  public List<OrderBean> searchByKeyword(OrderForm orderForm) {
    List<OrderBean> orderList = orderMapper.selectByKeyword(orderForm);
    return orderList;
  }

  // checkByTime
  public String checkByTime(String order_no) {
    String update_date = orderMapper.updateTime(order_no);
    return update_date;
  }
}
