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
/*
 * 自分の回答
 * @RequestMapping(value = "/account", produces="application/json;charset=UTF-8")
 *
 */
@RequestMapping("/bankTrading")
public class AccountController {

	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
    /**
     * 課題1. 口座開設
     *
     * 新規口座を開設する
     *
     * @return Account 新規登録した口座情報
     * 
     * 自分の回答
     * 	@PostMapping("bankTrading/open")
     * 	public Account open() {
     * 	Account account = new Account();
     * 	account.setAmount(0);
     * 	return this.accountService.save(account.getAmount());
     * 	}
     * 
     */
	
    @PostMapping("/open")
    public Account open() {
        return accountService.createAccount();
    }
	
    /**
     * 課題2. 残高照会
     *
     * 口座IDに紐づく残高を取得する
     *
     * @param accountId 口座ID
     * @return ResponseAmount 残高のレスポンス
     * 
     * 自分の回答
     * 
     * 	@GetMapping("bankTrading/{account_id}")
     * 	public ResponseAmount getAmount(@PathVariable("account_id")Integer accountId) {
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		// ResponseAmountインスタンスを生成
		ResponseAmount responseAmount = new ResponseAmount();
		
		// 対象口座の預金額をresponseAmount.amountにセットする
		responseAmount.setAmount(account.getAmount());
		
		return responseAmount;
		}
     */
    
    @GetMapping("/{account_id}")
    public ResponseAmount getAmount(@PathVariable("account_id") Integer accountId) {
        return accountService.getResponseAmount(accountId);
    }
	
    /**
     * 課題3. 預け入れ
     *
     * 預金後の残高を返却する
     *
     * @param accountId 口座ID
     * @param requestAmount 預金額
     * @return ResponseAmount 残高のレスポンス
     * 
     * 自分の回答
     * 
     * 	@PostMapping("bankTrading/deposit/{account_id}")
     * 	public ResponseAmount deposit(@PathVariable("account_id")Integer accountId, @RequestBody RequestAmount requestAmount) {
		
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		ResponseAmount responseAmount = new ResponseAmount();
		
		Integer addAmount = account.getAmount() + requestAmount.getAmount();
		
		responseAmount.setAmount(addAmount);
		
		return responseAmount;
		}
     */
    
    @PostMapping("/deposit/{account_id}")
    public ResponseAmount deposit(@PathVariable("account_id") Integer accountId, @RequestBody RequestAmount requestAmount) {
        return accountService.depositAccount(accountId, requestAmount);
    }
	
    /**
     * 課題4. 引き出し
     *
     * 引出後の残高を返却する
     *
     * @param accountId 口座ID
     * @param requestAmount 引出額
     * @return ResponseAmount 残高のレスポンス
     * 
     * 自分の回答
     * 
     * 	@PostMapping("bankTrading/withdraw/{account_id}")
     * 	public ResponseAmount withdraw(@PathVariable("account_id")Integer accountId, @RequestBody RequestAmount requestAmount) {
		
		// 取得した口座IDから対象口座を取得する
		Account account = this.accountService.findById(accountId);
		
		ResponseAmount responseAmount = new ResponseAmount();
		
		if (account.getAmount() - requestAmount.getAmount() >= 0) {
			Integer addAmount = account.getAmount() - requestAmount.getAmount();
			
			responseAmount.setAmount(addAmount);
		}
		
		return responseAmount;
		}
     */
    @PostMapping("/withdraw/{account_id}")
    public ResponseAmount withdraw(@PathVariable("account_id") Integer accountId, @RequestBody RequestAmount requestAmount) {
        return accountService.withdrawAccount(accountId, requestAmount);
    }
}



















