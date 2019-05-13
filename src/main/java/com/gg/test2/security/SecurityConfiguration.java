package com.gg.test2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserSecurityService userSecurityService;
	
	//重写了configure参数为AuthenticationManagerBuilder的方法
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
         //且该方法必须要实现UserDetailsService这个接口。
         auth.userDetailsService(userSecurityService)
             //密码使用BCryptPasswordEncoder()方法验证，因为这里使用了BCryptPasswordEncoder()方法验证。所以在注册用户的时候在接收前台明文密码之后也需要使用BCryptPasswordEncoder().encode(明文密码)方法加密密码。
             .passwordEncoder(new BCryptPasswordEncoder());;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
         super.configure(http);
    }

    @Override
	public void configure(WebSecurity web) throws Exception{
         super.configure(web);
    }
	
	
	
/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// temp 測試用 會從暫時的memory 中拿user 跟password 出來驗證
		 auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");

		// way 2 
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//auth.userDetailsService(userSecurityService).passwordEncoder(encoder);
	}
*/
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		// http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		// http.formLogin();
		// listener session
	}
	*/

}