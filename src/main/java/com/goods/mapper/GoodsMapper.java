package com.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.customer.bean.CustomerForm;
import com.goods.bean.GoodsBean;
import com.goods.bean.GoodsForm;

//DBにアクセスするマッパークラスです
@Transactional
@Mapper
public interface GoodsMapper {
	
	//全件取得
	public List<GoodsBean> selectAllGoods();
	
	//新規登録
	public void insertGoods(GoodsForm goodsForm);

	//詳細
	public List<GoodsBean> selectByNumber(Integer goods_no);
	
	//削除削除削除削除削除削除
	public void deleteByNumber(Integer goods_no);
	
	//編集
	public void updateGoods(GoodsForm goodsForm);
	
	//編集フォーム
	public GoodsForm createGoodsForm(Integer goods_no);
	
	//検索
	public List<GoodsBean> selectByKeyword(GoodsForm goodsForm);
}
