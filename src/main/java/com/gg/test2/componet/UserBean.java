package com.gg.test2.componet;

import org.springframework.stereotype.Component;

@Component
public class UserBean {
	private String name;
	private String email;
	
	public  UserBean() {
		name= "";
		email="";
	}

	@Override
	public String toString() {
		return "UserBean [name=" + name + ", email=" + email + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
