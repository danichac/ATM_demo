package com.danielc.services;

import com.danielc.services.utils.TransactionResult;

public interface AtmService {
	boolean logIn(String username, String password);
	boolean addAmount(double amount);
	TransactionResult retrieveAmount(double amount);
	double checkSavings();
}
