package com.customer.bean;

import lombok.Data;

/*
 * DBから顧客情報を受け取るリポジトリクラスです
 */

@Data
public class CustomerBean {

  private int cust_no;

  private String last_nm;

  private String first_nm;

  private byte gender_cd;

  private String post_number;

  private String home_address;

  private String mail_address;

  private String tel_no;

  private String entry_date;

  private String update_date;

  public int getCust_no() {
    return cust_no;
  }

  public void setCust_no(int cust_no) {
    this.cust_no = cust_no;
  }

  public String getLast_nm() {
    return last_nm;
  }

  public void setLast_nm(String last_nm) {
    this.last_nm = last_nm;
  }

  public byte getGender_cd() {
    return gender_cd;
  }

  public void setGender_cd(byte gender_cd) {
    this.gender_cd = gender_cd;
  }

  public String getFirst_nm() {
    return first_nm;
  }

  public void setFirst_nm(String first_nm) {
    this.first_nm = first_nm;
  }

  public String getPost_number() {
    return post_number;
  }

  public void setPost_number(String post_number) {
    this.post_number = post_number;
  }

  public String getHome_address() {
    return home_address;
  }

  public void setHome_address(String home_address) {
    this.home_address = home_address;
  }

  public String getMail_address() {
    return mail_address;
  }

  public void setMail_address(String mail_address) {
    this.mail_address = mail_address;
  }

  public String getTel_no() {
    return tel_no;
  }

  public void setTel_no(String tel_no) {
    this.tel_no = tel_no;
  }

  public String getEntry_date() {
    return entry_date;
  }

  public void setEntry_date(String entry_date) {
    this.entry_date = entry_date;
  }

  public String getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(String update_date) {
    this.update_date = update_date;
  }

}
