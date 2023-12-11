package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.resource.RequestAmount;
import com.example.resource.ResponseAmount;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	
	@Autowired
	// コンストラクタインジェクション
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	 /**
     * 新規口座作成処理
     *
     * @return Account 新規登録した口座情報
     */
    public Account createAccount() {
        Account account = new Account();
        account.setAmount(0);
        return this.accountRepository.save(account);
    }

    /**
     * 残高取得処理
     *
     * @param accountId 口座ID
     * @return ResponseAmount 残高のレスポンス
     */
    public ResponseAmount getResponseAmount(Integer accountId) {
        Account account = accountRepository.findById(accountId).get();
        ResponseAmount responseAmount = new ResponseAmount();
        responseAmount.setAmount(account.getAmount());
        return responseAmount;
    }

    /**
     * 預入処理
     *
     * @param accountId 口座ID
     * @param requestAmount 預金額
     * @return ResponseAmount 残高のレスポンス
     */
    public ResponseAmount depositAccount(Integer accountId, RequestAmount requestAmount) {
        Account account = accountRepository.findById(accountId).get();
        account.setAmount(requestAmount.getAmount() + account.getAmount());
        account = accountRepository.save(account);

        ResponseAmount depositBalance = new ResponseAmount();
        depositBalance.setAmount(account.getAmount());
        return depositBalance;
    }

    /**
     * 引出処理
     *
     * @param accountId 口座のID
     * @param requestAmount 引出額
     * @return ResponseAmount 残高のレスポンス
     */
    public ResponseAmount withdrawAccount(Integer accountId, RequestAmount requestAmount) {
        Account account = accountRepository.findById(accountId).get();
        if (requestAmount.getAmount() <= account.getAmount()) {
            account.setAmount(account.getAmount() - requestAmount.getAmount());
            accountRepository.save(account);
        }

        ResponseAmount depositBalance = new ResponseAmount();
        depositBalance.setAmount(account.getAmount());
        return depositBalance;
    }
	
//	自分の回答
//	public Account save(Integer amount) {
//		Account account = new Account();
//		
//		// 
//		account.setAmount(amount);
//		
//		return this.accountRepository.save(account);
//	}
//	
//	public Account findById(Integer id) {
//		
//		// IDカラムでデータ検索するメソッド
//		Optional<Account> optionalAccount = this.accountRepository.findById(id);
//			
//		Account account = optionalAccount.get();
//		
//		return account;
//	}
}
