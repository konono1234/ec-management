package com.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
