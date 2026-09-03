package com.apnabank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnabank.entities.Account;
import com.apnabank.service.AccountService;


@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		
		Account createAccount=accountService.createAccount(account);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountDetailsByAccountNum(@PathVariable Long accountNumber) {
		Account savedAccount=accountService.getAccountDetailsByAccountNumber(accountNumber);
		
		return savedAccount;
	}
	
	@GetMapping("/findAll")
	public List<Account> getAllAccountDetails(){
		List<Account> listAccount= accountService.getAllAccountDetails();
		return listAccount;
		
	}
	
	@PutMapping("/deposit/{accountNumber}/{amount}")
	public Account depositAmount(@PathVariable Long accountNumber,@PathVariable Double amount) {
		Account depositedAccount=accountService.depositAmount(accountNumber, amount);
		return depositedAccount;
	}
	
	@PutMapping("/withdrawl/{accountNumber}/{amount}")
	public Account withdrawlAmount(@PathVariable Long accountNumber,@PathVariable Double amount) {
		Account accountAfterWithdrawl=accountService.withdrawAmount(accountNumber, amount);
		return accountAfterWithdrawl;
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
		
		boolean b=accountService.closeAccount(accountNumber);
		if(b) {
			return ResponseEntity.ok("Account Deleted Successfully");
		}
		
		return ResponseEntity.ok("Account Deleted Failed due to account not present in your database");
	}
	
}
