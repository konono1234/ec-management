package com.goods.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customer.bean.CustomerForm;
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
	
	/*
	 * 新規登録画面にcustomerFormのインスタンスと共に遷移します
	 */
	@RequestMapping(value = "/goods/g_create")
	public String create(@ModelAttribute("goodsForm") GoodsForm goodsForm, Model model) {
		return "goods/g_create";
	}

	/*
	 * DBにInsertして保存完了画面に遷移します
	 */
	@RequestMapping(value = "/goods/save-create")
	public String saveForm(@ModelAttribute @Validated GoodsForm goodsForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "goods/g_create";
		} else {
			try {
				goodsService.createGoods(goodsForm);
				model.addAttribute("goodsForm", goodsForm);
				return "goods/g_save";

			} catch (Exception e) {
				model.addAttribute("errorMessage", "商品番号が重複しています。別の番号を指定してください");
				return "goods/g_create";
			}

		}
	
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
	
	/*
	 * 削除機能です。削除画面は用意せずにdeleteしたら一覧画面に遷移します。
	 */
	@RequestMapping(value = "/goods/delete/{goods_no}")
	public String deleteForm(@PathVariable Integer goods_no, RedirectAttributes attributes,
			Model model) {
		goodsService.deleteGoods(goods_no);

		attributes.addFlashAttribute("deleteMessage", "商品番号:" + goods_no + "を削除しました");

		return "redirect:/goods";
	}
	
	/*
	 * 検索機能です。CustomerFormのkeyとkeywordを使いDBに検索を掛けます。
	 */
	@RequestMapping(value = "/goods/g_search", method = RequestMethod.POST)
	public String searchForm(GoodsForm goodsForm, Model model) {

		if (goodsForm.getKeyword().equals("")) {
			model.addAttribute("errorMessage", "キーワードを入力してください");
			return "goods/g_search";
		}
		List<GoodsBean> searchList = new ArrayList<GoodsBean>();

		searchList = goodsService.searchByKeyword(goodsForm);
		model.addAttribute("searchList", searchList);

		try {
			if (searchList.get(0) == null) {
			}
		} catch (Exception e) {
			model.addAttribute("emptyMessage", "検索結果は0件です");
			return "goods/g_search";
		}
		
		for(int i = 0; i < searchList.size(); i++) {
			System.out.println(searchList.get(i));
		}
		
		return "goods/g_search";
	}
	
	/*
	 * 更新画面に顧客情報が入ったデータを渡すメソッドです。
	 */
	@RequestMapping(value = "/goods/g_edit/{goods_no}", method = RequestMethod.GET)
	public String editForm(@ModelAttribute GoodsForm goodsForm, @PathVariable Integer goods_no,
			Model model) {

		goodsForm = goodsService.editGoodsForm(goods_no);
		model.addAttribute("goodsForm", goodsForm);
		
		return "goods/g_edit";
	}

	/*
	 * 編集機能です。入力エラーの場合更新しません
	 */
	@RequestMapping(value = "/goods/save-edit")
	public String updateSave(@ModelAttribute @Validated GoodsForm goodsForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ("goods/g_edit");
		} else {
			goodsService.editGoods(goodsForm);
			return ("goods/g_save");
		}
	}
}
