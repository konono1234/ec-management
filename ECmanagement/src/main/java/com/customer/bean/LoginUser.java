package com.customer.bean;

import lombok.Data;

//ユーザー情報のBeanクラス。DBから取り出したデータを格納する倉庫
@Data
public class LoginUser {
	
	//DB機能がまだ実装されていないためIDとpassを直接定義します
	private String user_id = "kikuchi";
	
	private String password = "1234";

}
