package com.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goods.bean.GoodsBean;
import com.goods.bean.GoodsForm;
import com.goods.service.GoodsService;

//Serviceを起動したりViewにデータを渡す商品情報コントローラークラスです
@Controller
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	@RequestMapping(value = "/goods")
	public String initIndex(Model model) {

	    /*
	     * 一覧表示機能
	     */
	    List<GoodsBean> goodsList = goodsService.selectAllGoods();
	    model.addAttribute("goodsList", goodsList);
	    model.addAttribute("goodsForm", new GoodsForm());
	    return "goods/g_index";
	  }
	
}
