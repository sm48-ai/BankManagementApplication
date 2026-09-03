package com.apnabank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnabank.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	

}
