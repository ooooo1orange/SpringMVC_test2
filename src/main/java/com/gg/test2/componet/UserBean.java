package com.gg.test2.componet;

import org.springframework.stereotype.Component;

@Component
public class UserBean {
	private String name;
	private String email;
	private String work_id;
	private String password;

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean() {
		name = "";
		email = "";
		work_id = "";
		password = "";
	}

	@Override
	public String toString() {
		return "UserBean [name=" + name + ", email=" + email + ", work_id=" + work_id + ", password=" + password + "]";
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
