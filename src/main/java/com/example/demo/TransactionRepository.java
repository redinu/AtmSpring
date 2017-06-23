package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	
	List<Transaction> findByAcctNo(long acctNo);

}
