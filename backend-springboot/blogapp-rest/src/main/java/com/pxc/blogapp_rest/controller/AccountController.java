package com.pxc.blogapp_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pxc.blogapp_rest.model.Account;
import com.pxc.blogapp_rest.model.dto.AccountDto;
import com.pxc.blogapp_rest.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("register")
	public ResponseEntity<String> registerNewAcc(@RequestBody AccountDto accountDto){		
		Account savedAccount = accountService.registerNewUser(accountDto);
		if(savedAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>("created", HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<Account> loginToAcc(@RequestBody AccountDto accountDto){
		
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						accountDto.getUsername(), accountDto.getPassword()
						));
		if(authentication.isAuthenticated()) {
			Account account = accountService.findAccBuUsername(authentication.getName());
			
			return new ResponseEntity<>(account,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
