package com.pxc.blogapp_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pxc.blogapp_rest.dao.AccountRepo;
import com.pxc.blogapp_rest.model.Account;
import com.pxc.blogapp_rest.model.dto.AccountDto;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public Account registerNewUser(AccountDto accountDto) {
		Account findAccount = accountRepo.findByUsername(accountDto.getUsername()).orElse(null);
		if(findAccount == null) {
			Account account = new Account(accountDto);
			account.setPassword(encoder.encode(accountDto.getPassword()));
			return accountRepo.save(account);
		}
		return null;
	}

	public Account findAccBuUsername(String name) {
		return accountRepo.findByUsername(name).get();
	}

}
