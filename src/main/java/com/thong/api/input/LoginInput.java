package com.thong.api.input;

import javax.validation.constraints.NotBlank;

import com.thong.annotation.IsExist;


public class LoginInput {

	@NotBlank(message="username can not not be blank")
	private String username;
	
	@NotBlank(message="password can not not be blank")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
