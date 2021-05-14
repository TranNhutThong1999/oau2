package com.thong.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thong.annotation.IsExist;
import com.thong.entity.Role;

public class UserDTO extends CommonDTO {
	//@IsExist
	@NotBlank(message = "username can not be blank")
	private String userName;

	@NotBlank(message = "password can not be blank")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String firstName;
	private String lastName;

	@NotBlank(message = "email can not be blank")
	@Email(message = "wrong email ")
	private String email;

	private String phone;
	private boolean isActive;
	private boolean nonBlock;
	private List<String> roles;
	private int money;
	private String address;

	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	// @jso
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isNonBlock() {
		return nonBlock;
	}

	public void setNonBlock(boolean nonBlock) {
		this.nonBlock = nonBlock;
	}

	

}
