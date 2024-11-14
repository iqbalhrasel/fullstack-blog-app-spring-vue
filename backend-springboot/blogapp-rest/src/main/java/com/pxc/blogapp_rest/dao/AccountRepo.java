package com.pxc.blogapp_rest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	Optional<Account> findByUsername(String username);

}
