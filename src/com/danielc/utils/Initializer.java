package com.danielc.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.danielc.entities.Account;
import com.danielc.entities.Customer;
import com.danielc.repositories.CustomerRepository;

public class Initializer {
	private final CustomerRepository repo;

	public Initializer(CustomerRepository repo) {
		this.repo = repo;
	}
	
	public void CreateCustomers() {
		String password = "secret-pass";
		
		repo.save(new Customer("user1", password, "John", "Smith"));
		repo.save(new Customer("user2", password, "Mary", "Lawrence"));
		repo.save(new Customer("user3", password, "Susan", "Miller"));
		
		Account acc1 = new Account(1, 800);
		Account acc2 = new Account(2);
		Account acc3 = new Account(3, 300);
		
		repo.findById(1L).orElse(new Customer()).setAccount(acc1);
		repo.findById(2L).orElse(new Customer()).setAccount(acc2);
		repo.findById(3L).orElse(new Customer()).setAccount(acc3);
	}
}
