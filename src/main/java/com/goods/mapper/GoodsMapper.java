package com.goods.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.customer.bean.CustomerBean;
import com.goods.bean.GoodsBean;

//DBにアクセスするマッパークラスです
@Transactional
@Mapper
public interface GoodsMapper {
	
	//全件取得
	public List<GoodsBean> selectAllGoods();

	//詳細
	public List<GoodsBean> selectByNumber(Integer goods_no);
}
