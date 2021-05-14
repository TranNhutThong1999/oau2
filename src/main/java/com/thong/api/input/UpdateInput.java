package com.thong.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import com.thong.entity.User;

public class UpdateInput {
	private String password;
	private String firstName;
	
	@Min(5)
	private String lastName;
	
	@Email
	private String email;
	private String phone;
	
	public User toEntity() {
		User u = new User();
		u.setPassword(this.password);
		u.setFirstName(this.firstName);
		u.setLastName(this.lastName);
		u.setEmail(this.email);
		u.setPhone(this.phone);
		return u;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
