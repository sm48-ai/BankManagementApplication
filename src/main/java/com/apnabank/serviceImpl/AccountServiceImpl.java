package com.apnabank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnabank.entities.Account;
import com.apnabank.repo.AccountRepository;
import com.apnabank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		
		Account saveAccount=accountRepository.save(account);
		
		return saveAccount;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> accountFromDB=accountRepository.findById(accountNumber);
		if(accountFromDB.isEmpty()) {
			throw new RuntimeException("Account does not exists");
		}else {
			return accountFromDB.get();
		}
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> accountList=accountRepository.findAll();
		if(accountList.isEmpty()) {
			throw new RuntimeException("No Account is present in your db");
		}
		return accountList;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		
		Account savedAccount=getAccountDetailsByAccountNumber(accountNumber);
		Double totalAmount=savedAccount.getAccount_balance()+amount;
		savedAccount.setAccount_balance(totalAmount);
		accountRepository.save(savedAccount);
		return savedAccount;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		
		Account savedAccount=getAccountDetailsByAccountNumber(accountNumber);
		Double amountAfterWithdrawl=savedAccount.getAccount_balance()-amount;
		savedAccount.setAccount_balance(amountAfterWithdrawl);
		accountRepository.save(savedAccount);
		return savedAccount;
	}

	@Override
	public boolean closeAccount(Long accountNumber) {
		Optional<Account> acc=accountRepository.findById(accountNumber);
		if(acc.isEmpty()) {
			return false;
		}
		accountRepository.delete(acc.get());
		return true;
	
	}

}
