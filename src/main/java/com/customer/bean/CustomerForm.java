package com.customer.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

/*
 * 入力フォームから受け取ったデータを一時的に格納するフォームです。DBのためにデータ型を変換したりします。
 */

public class CustomerForm {

  @NotNull(message = "{NotNull}")
  private Integer cust_no;

  @Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
  @Size(min = 1, max = 8, message = "{Size}")
  private String check_no;

  @NotNull(message = "{NotNull}")
  @Size(min = 1, max = 16, message = "{Size}")
  private String last_nm;

  @NotNull(message = "{NotNull}")
  @Size(min = 1, max = 16, message = "{Size}")
  private String first_nm;

  private byte gender_cd;

  @NotNull(message = "{NotNull}")
  @Length(min = 8, max = 8, message = "{Size}")
  @Pattern(regexp = "^([0-9]{3}[-]?[0-9]{4})?$", message = "{0}は〇〇〇-〇〇〇〇形式で入力してください")
  private String post_number;

  @NotNull(message = "{NotNull}")
  @Size(min = 1, max = 48, message = "{Size}")
  private String home_address;

  @Size(min = 0, max = 100, message = "{Size}")
  @Email(message = "{Email}")
  private String mail_address;

  @NotNull(message = "{NotNull}")
  @Size(min = 1, max = 13, message = "{Size}")
  private String tel_no;

  Calendar calender = Calendar.getInstance();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  private String entry_date = sdf.format(calender.getTime());

  private Date sql_entry_date = Date.valueOf(entry_date);

  private String key;

  private String keyword;

  public Integer getCust_no() {
    return cust_no;
  }

  public void setCust_no(Integer cust_no) {
    this.cust_no = cust_no;
  }

  public void setCheck_no(String check_no) {
    this.check_no = check_no;
    try {
      if (this.check_no != null) {
        this.cust_no = Integer.parseInt(check_no);
      }
    } catch (Exception e) {

    }
  }

  public String getCheck_no() {
    return check_no;
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

  public Date getSql_entry_date() {
    return sql_entry_date;
  }

  public void setReg_date(String entry_date) {
    this.entry_date = entry_date;
    this.sql_entry_date = Date.valueOf(entry_date);
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
