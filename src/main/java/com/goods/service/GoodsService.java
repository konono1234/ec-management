package com.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;
import com.goods.bean.GoodsBean;
import com.goods.bean.GoodsForm;
import com.goods.mapper.GoodsMapper;

//商品情報を取り出すサービスクラスです
@Service
public class GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;
	
	//全件取得
	public List<GoodsBean> selectAllGoods() {
		List<GoodsBean> goodsList = goodsMapper.selectAllGoods();
		return goodsList;
	}
	
	//新規登録
	public void createGoods(GoodsForm goodsForm) {
		goodsMapper.insertGoods(goodsForm);
	}
	
	//詳細
	public List<GoodsBean> findByNumber(Integer goods_no) {
		List<GoodsBean> goodsList = goodsMapper.selectByNumber(goods_no);
	    return goodsList;
	}
	
	//削除削除削除削除
	public void deleteGoods(Integer goods_no) {
		goodsMapper.deleteByNumber(goods_no);
	}
	
	//編集
	public void editGoods(GoodsForm goodsForm) {
		goodsMapper.updateGoods(goodsForm);
	}

	//編集フォーム
	public GoodsForm editGoodsForm(Integer goods_no) {
		GoodsForm goodsForm = goodsMapper.createGoodsForm(goods_no);
		return goodsForm;
	}
	  
	//検索
	public List<GoodsBean> searchByKeyword(GoodsForm goodsForm) {
		List<GoodsBean> goodsList = goodsMapper.selectByKeyword(goodsForm);
		return goodsList;
	}

}
