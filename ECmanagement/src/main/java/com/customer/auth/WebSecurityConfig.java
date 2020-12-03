package com.customer.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.customer.service.UserService;

//ログイン機能を設定するクラス
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	  

	  @Autowired
	  private UserService userService;

	  // パスワードのハッシュ化 パスワードエンコーダー
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

	    return bcpe;
	  }

	  // 静的リソースのアクセスにはセキュリティを適用しない
	  public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/webjars/**");
	    web.ignoring().antMatchers("/css/**");
	    web.ignoring().antMatchers("/images/**");
	    web.ignoring().antMatchers("/login-edit/**");
	    web.ignoring().antMatchers("/login-editPs/**");
	    web.ignoring().antMatchers("/login-edit-save/**");
	    web.ignoring().antMatchers("/login-create/**");
	    web.ignoring().antMatchers("/login-create-save/**");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().mvcMatchers("/login").permitAll() // ログイン画面は誰にでも入れるよ
	        .mvcMatchers("/admin/**").hasRole("ADMIN") // adminしか閲覧できない、が実装は後回し
	        .anyRequest().authenticated().and() // それ以外は認証が必要
	        .formLogin().loginPage("/login") // ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
	        .loginProcessingUrl("/login") // ログインフォームのアクションに指定したURL[action="@{/login}"]を設定
	        .usernameParameter("username") // リクエストパラメータのname属性を明示
	        .passwordParameter("password") // リクエストパラメータのpassword属性を明示※下で設定可
	        .defaultSuccessUrl("/customer") // ログイン成功時のurl
	        .failureUrl("/login?error").permitAll().and() // ログイン失敗時のurl。パラメータでerrorを付加するｔｈ：ifでhtml上で拾うため
	        .logout().logoutUrl("/logout") // ログアウト用のurl
	        .logoutSuccessUrl("/login?logout") // ログアウト成功時のurl。パラメータでlogoutを付加する
	        .deleteCookies("JSESSIONID") // ログアウト時に削除するクッキー名
	        .invalidateHttpSession(true).permitAll();// ログアウト時のセッション破棄を有効化
	  }

	  // idとpasswordをサービスを介して習得し設定する
	  @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

	  }

}
