package com.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.bean.CustomerBean;
import com.goods.bean.GoodsBean;
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
	
	//詳細
	public List<GoodsBean> findByNumber(Integer goods_no) {
		List<GoodsBean> goodsList = goodsMapper.selectByNumber(goods_no);
	    return goodsList;
	}

}
