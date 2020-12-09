package com.order.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class OrderForm {

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotBlank(message = "{NotBlank}")
  private String order_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotBlank(message = "{NotBlank}")
  private String goods_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotBlank(message = "{NotBlank}")
  private String cust_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotBlank(message = "{NotBlank}")
  private String order_numbers;

  Calendar calender = Calendar.getInstance();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  private String order_date = sdf.format(calender.getTime());

  private Date sql_order_date = Date.valueOf(order_date);

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @NotBlank(message = "{NotBlank}")
  private String store_no;

  private String key;

  private String keyword;

  public String getOrder_no() {
    return order_no;
  }

  public void setOrder_no(String order_no) {
    this.order_no = order_no;
  }

  public String getGoods_no() {
    return goods_no;
  }

  public void setGoods_no(String goods_no) {
    this.goods_no = goods_no;
  }

  public String getCust_no() {
    return cust_no;
  }

  public void setCust_no(String cust_no) {
    this.cust_no = cust_no;
  }

  public String getOrder_numbers() {
    return order_numbers;
  }

  public void setOrder_numbers(String order_numbers) {
    this.order_numbers = order_numbers;
  }

  public String getOrder_date() {
    return order_date;
  }

  public void setOrder_date(String order_date) {
    this.order_date = order_date;
    this.sql_order_date = Date.valueOf(order_date);
  }

  public String getStore_no() {
    return store_no;
  }

  public void setStore_no(String store_no) {
    this.store_no = store_no;
  }

  public Date getSql_order_date() {
    return sql_order_date;
  }



  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
    if (this.getKey().equals("gender_cd") && keyword.equals("男")) {
      this.keyword = "1";
    } else if (this.getKey().equals("gender_cd") && keyword.equals("女")) {
      this.keyword = "2";
    } else if (this.getKey().equals("gender_cd") && keyword.equals("未定義")) {
      this.keyword = "3";
    } else {
      this.keyword = keyword;
    }
  }

}
