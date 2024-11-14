package com.pxc.blogapp_rest.model.dto;

public class AccountDto {
	private String fullName;
	private String username;
	private String password;
	
	public AccountDto() {
	}

	public AccountDto(String fullName,String username, String password) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

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

	@Override
	public String toString() {
		return "AccountDto [fullName=" + fullName + ", username=" + username + ", password=" + password + "]";
	}

}
