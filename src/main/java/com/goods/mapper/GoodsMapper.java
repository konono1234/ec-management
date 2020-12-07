package com.goods.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import com.goods.bean.GoodsBean;

//DBにアクセスするマッパークラスです
@Transactional
@Mapper
public interface GoodsMapper {
	
	//全件取得
	public List<GoodsBean> selectAllGoods();

}
