package com.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.customer.bean.CustomerBean;
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
	
	//商品詳細機能です。商品番号を受け取り詳細画面のに遷移します
	@RequestMapping(value = "/goods/{goods_no}", method = RequestMethod.GET)
	public ModelAndView detailForm(@PathVariable Integer goods_no) {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("goods/g_detail");
	    List<GoodsBean> goodsList = goodsService.findByNumber(goods_no);
	    mv.addObject("goodsList", goodsList);
	    for (int i=0; i<goodsList.size(); ++i) {
	        System.out.println(goodsList.get(i));
	    }
	    return mv;
	}
}
