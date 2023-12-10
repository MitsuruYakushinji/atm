package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	
	@Autowired
	// コンストラクタインジェクション
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account save(Integer amount) {
		Account account = new Account();
		
		// 
		account.setAmount(amount);
		
		return this.accountRepository.save(account);
	}
	
	public Account findById(Integer id) {
		
		// IDカラムでデータ検索するメソッド
		Optional<Account> optionalAccount = this.accountRepository.findById(id);
			
		Account account = optionalAccount.get();
		
		return account;
	}
}
