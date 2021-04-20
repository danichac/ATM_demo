package com.danielc.services;

import java.util.Collection;
import java.util.Optional;

import com.danielc.entities.Account;
import com.danielc.entities.Customer;
import com.danielc.repositories.CustomerRepository;
import com.danielc.services.utils.TransactionResult;

public class AtmServiceImpl implements AtmService {
	private final double MAX_AMOUNT_TO_RETRIEVE = 200.0;
	private final CustomerRepository repo;
	private Customer customer;

	public AtmServiceImpl(CustomerRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean logIn(String username, String password) {
		Collection<Customer> customers = (Collection<Customer>)repo.findAll();
		Optional<Customer> customer = customers.stream()
										.filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
										.findAny();
		
		boolean success = customer.isPresent();
		this.customer = customer.orElse(new Customer()); 
		return success;
	}

	@Override
	public boolean addAmount(double amount) {
		try {
			customer.getAccount().addToSavings(amount);
			return true;
		} catch(Exception ex) {
			return false;
		}			
	}

	@Override
	public TransactionResult retrieveAmount(double amount) {
		Account acc = customer.getAccount();
		String message;
		double retrieved = 0;
		boolean succeeded = false;
		
		if(amount > MAX_AMOUNT_TO_RETRIEVE) {
			message = String.format("You can only retrieve $%.2f at a time.", MAX_AMOUNT_TO_RETRIEVE);
			return new TransactionResult(succeeded, message, retrieved);
		}
		if(amount > acc.getTotalSavings()) {
			message = "Insuficient funds.";
			return new TransactionResult(succeeded, message, retrieved);
		}
		
		retrieved = acc.retrieveFromSavings(amount);
		message = String.format("You have retrieved $%.2f from your savings account.", retrieved);
		succeeded = true;
		
		return new TransactionResult(succeeded, message, retrieved);
	}

	@Override
	public double checkSavings() {
		return customer.getAccount().getTotalSavings();
	}

}
