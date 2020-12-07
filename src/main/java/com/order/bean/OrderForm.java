package com.order.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrderForm {

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotNull(message = "{NotNull}")
  private Integer order_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotNull(message = "{NotNull}")
  private Integer goods_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotNull(message = "{NotNull}")
  private Integer cust_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotNull(message = "{NotNull}")
  private Integer order_numbers;

  Calendar calender = Calendar.getInstance();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  private String order_date = sdf.format(calender.getTime());

  private Date sql_order_date = Date.valueOf(order_date);

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotNull(message = "{NotNull}")
  private Integer store_no;

  public Integer getOrder_no() {
    return order_no;
  }

  public void setOrder_no(Integer order_no) {
    this.order_no = order_no;
  }

  public Integer getGoods_no() {
    return goods_no;
  }

  public void setGoods_no(Integer goods_no) {
    this.goods_no = goods_no;
  }

  public Integer getCust_no() {
    return cust_no;
  }

  public void setCust_no(Integer cust_no) {
    this.cust_no = cust_no;
  }

  public Integer getOrder_numbers() {
    return order_numbers;
  }

  public void setOrder_numbers(Integer order_numbers) {
    this.order_numbers = order_numbers;
  }

  public String getOrder_date() {
    return order_date;
  }

  public void setOrder_date(String order_date) {
    this.order_date = order_date;
  }

  public Integer getStore_no() {
    return store_no;
  }

  public void setStore_no(Integer store_no) {
    this.store_no = store_no;
  }

  public Date getSql_order_date() {
    return sql_order_date;
  }

}
