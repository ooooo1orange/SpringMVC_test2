package com.gg.test2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gg.test2.componet.UserBean;
import com.gg.test2.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public List<UserBean> GetUser() {
		return userRepository.getUser();
	}
	public List<UserBean> GetUser2() {
		return userRepository.getUser2();
	}
	
}
