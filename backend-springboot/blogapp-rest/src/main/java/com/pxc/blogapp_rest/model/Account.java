package com.pxc.blogapp_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pxc.blogapp_rest.model.dto.AccountDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fullName;
	
	private String username;
	
	@JsonIgnore
	private String password;

	public Account() {
	}

	public Account(int id, String fullName, String username, String password) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
	}
	
	public Account(AccountDto accountDto) {
		this.fullName = accountDto.getFullName();
		this.username = accountDto.getUsername();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Account [id=" + id + ", fullName=" + fullName + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	

}
