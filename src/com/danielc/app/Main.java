package com.danielc.app;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.danielc.repositories.CustomerRepositoryImpl;
import com.danielc.services.AtmInteraction;
import com.danielc.services.AtmService;
import com.danielc.services.AtmServiceImpl;
import com.danielc.utils.Initializer;

public class Main {

	public static void main(String[] args) {
		CustomerRepositoryImpl repo = new CustomerRepositoryImpl();
		Initializer init = new Initializer(repo);
		init.CreateCustomers();
		AtmService atmService = new AtmServiceImpl(repo);
		AtmInteraction atm = new AtmInteraction(atmService, repo);
		
		
		atm.mainSelection();
	}

}
