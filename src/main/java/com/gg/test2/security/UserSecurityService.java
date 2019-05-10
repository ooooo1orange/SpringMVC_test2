package com.gg.test2.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gg.test2.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
	
	@Autowired
	UserRepository ur ;
	
	@Transactional
	public UserDetails loadUserByUsername(String work_id) throws UsernameNotFoundException {
		String pwd = ur.getUserPWD(work_id);
		GrantedAuthority authrole = new SimpleGrantedAuthority("USER");
		List<GrantedAuthority> listAuth = new ArrayList<>();
		listAuth.add(authrole);
		UserDetails userdeail = new User(work_id,pwd,listAuth);
		return userdeail;
	}

}
