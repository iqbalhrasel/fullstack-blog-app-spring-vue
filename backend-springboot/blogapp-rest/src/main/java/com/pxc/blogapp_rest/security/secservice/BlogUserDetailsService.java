package com.pxc.blogapp_rest.security.secservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pxc.blogapp_rest.dao.AccountRepo;
import com.pxc.blogapp_rest.model.Account;
import com.pxc.blogapp_rest.security.secmodel.AccountPrincipal;

@Service
public class BlogUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepo.findByUsername(username).get();
		return new AccountPrincipal(account);
	}

}
