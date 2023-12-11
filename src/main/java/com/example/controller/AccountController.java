package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.resource.RequestAmount;
import com.example.resource.ResponseAmount;
import com.example.service.AccountService;

@RestController
@RequestMapping(value = "/account", produces="application/json;charset=UTF-8")
public class AccountController {

	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("bankTrading/open")
	public Account open() {
		Account account = new Account();
		account.setAmount(0);
		
		return this.accountService.save(account.getAmount());
	}
	
	@GetMapping("bankTrading/{account_id}")
	public ResponseAmount getAmount(@PathVariable("account_id")Integer accountId) {
		
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		// ResponseAmountインスタンスを生成
		ResponseAmount responseAmount = new ResponseAmount();
		
		// 対象口座の預金額をresponseAmount.amountにセットする
		responseAmount.setAmount(account.getAmount());
		
		return responseAmount;
	}
	
	@PostMapping("bankTrading/deposit/{account_id}")
	public ResponseAmount deposit(@PathVariable("account_id")Integer accountId, @RequestBody RequestAmount requestAmount) {
		
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		ResponseAmount responseAmount = new ResponseAmount();
		
		Integer addAmount = account.getAmount() + requestAmount.getAmount();
		
		responseAmount.setAmount(addAmount);
		
		return responseAmount;
	}
	
	@PostMapping("bankTrading/withdraw/{account_id}")
	public ResponseAmount withdraw(@PathVariable("account_id")Integer accountId, @RequestBody RequestAmount requestAmount) {
		
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		ResponseAmount responseAmount = new ResponseAmount();
		
		if (account.getAmount() - requestAmount.getAmount() >= 0) {
			Integer addAmount = account.getAmount() - requestAmount.getAmount();
			
			responseAmount.setAmount(addAmount);
		}
		
		return responseAmount;
	}
}



















