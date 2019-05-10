package com.gg.test2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserSecurityService userSecurityService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// temp 測試用 會從暫時的memory 中拿user 跟password 出來驗證
		// auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");

		/* way 2 */
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userSecurityService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		//http.formLogin();
		//listener session
	}

}