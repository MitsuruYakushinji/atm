package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	
	@Autowired
	// コンストラクタインジェクション
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
}
