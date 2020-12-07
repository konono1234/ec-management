package com.order.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import com.order.bean.OrderBean;
import com.order.bean.OrderForm;

/*
 * DBにアクセスするマッパークラスです
 */
@Transactional
@Mapper
public interface OrderMapper {

  // select *
  public List<OrderBean> selectAllOrder();

  // create
  public void insertOrder(OrderForm orderForm);

  // detail
  public List<OrderBean> selectByNumber(Integer order_no);

  // delete
  public void deleteByNumber(Integer order_no);

  // edit
  public void updateOrder(OrderForm orderForm);

  // editForm
  public OrderForm createOrderForm(Integer order_no);

  // search
  public List<OrderBean> selectByKeyword(OrderForm orderForm);

}
