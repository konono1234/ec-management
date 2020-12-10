package com.goods.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * 入力フォームから受け取ったデータを一時的に格納するフォームです。DBのためにデータ型を変換したりします。
 */
public class GoodsForm {
	
	@NotNull(message = "{NotNull}")
	private Integer goods_no;
	
	@Pattern(regexp = "^[0-9]*$", message = "{0}は半角数字のみで入力してください")
	@Size(min = 1, max = 8, message = "{Size}")
	private String check_no;
	
	@NotNull(message = "{NotNull}")
	private String goods_name;
	
	@NotNull(message = "{NotNull}")
	private Integer price;
	
	private Integer stock;
	
	@NotNull(message = "{NotNull}")
	private byte category_no;
	
	Calendar calender = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String reg_date = sdf.format(calender.getTime());
	
	private Date sql_reg_date = Date.valueOf(reg_date);

	private String key;

	private String keyword;
	
	private String goods_comment;
	
	public Integer getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(Integer goods_no) {
		this.goods_no = goods_no;
	}
	
	public String getCheck_no() {
		return check_no;
	}
	  
	public void setCheck_no(String check_no) {
		this.check_no = check_no;
		try {
			if (this.check_no != null) {
				this.goods_no = Integer.parseInt(check_no);
			}
		} catch (Exception e) {

		}
	}
	
	public String getGoods_name() {
		return goods_name;
	}
	
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public byte getCategory_no() {
		return category_no;
	}
	
	public void setCategory_no(byte category_no) {
		this.category_no = category_no;
	}
	
	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public Date getSql_reg_date() {
		return sql_reg_date;
	}

	public void setSql_Reg_date(String reg_date) {
		this.reg_date = reg_date;
		this.sql_reg_date = Date.valueOf(reg_date);
	}
	  
	public String getKeyword() {
		return keyword;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
		if (this.getKey().equals("category_no") && keyword.equals("工具")) {
			this.keyword = "1";
		} else if (this.getKey().equals("category_no") && keyword.equals("農業・園芸資材")) {
			this.keyword = "2";
		} else if (this.getKey().equals("category_no") && keyword.equals("未定義")) {
			this.keyword = "3";
		} else {
			this.keyword = keyword;
		}
	}
	
	public String getGoods_comment() {
		return goods_comment;
	}
	
	public void setGoods_comment(String goods_comment) {
		this.goods_comment = goods_comment;
	}
}
