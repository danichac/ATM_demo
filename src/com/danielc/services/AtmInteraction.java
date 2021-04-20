package com.danielc.services;

import java.util.Scanner;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.danielc.entities.Account;
import com.danielc.entities.Customer;
import com.danielc.repositories.CustomerRepository;
import com.danielc.services.utils.TransactionResult;


public class AtmInteraction {
	private final AtmService atmService;
	private final CustomerRepository repo;
	private boolean loggedIn;
	private int option;
	private static long generatedId;
	
	public AtmInteraction(AtmService atmService, CustomerRepository repo) {
		this.atmService = atmService;
		this.repo = repo;
		generatedId = repo.count() + 1;
	}
	
	private void mainMenu() {
		System.out.println("=================================================");
		System.out.println("|                     ATM                       |");
		System.out.println("=================================================");
		
		System.out.println();
		System.out.println("Options:");
		System.out.println("\t1. Create account.");
		System.out.println("\t2. Log in.");
		System.out.println("\t3. Exit.");
		
		System.out.println();
		System.out.println("Enter your option: ");
	}
	
	public void mainSelection() {
		option = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(option <= 0 || option > 3) {
			mainMenu();
			try {
				option = scanner.nextInt();
			}
			catch(Exception ex) {
				System.out.println("The only valid options are: 1, 2 and 3.");
				System.out.println("Press enter to try again.");
				scanner.nextLine();
			}
			
			switch(option) {
			case 1:
				createAccount();
				break;
			case 2:
				logIn();
				if(loggedIn)
					atmSelection();
				break;
			case 3:
				System.out.println();
				System.out.println("Thank you for using the ATM!");
				System.exit(0);
			}
			
		}
		
		
	}
	
	private void atmSelection() {
		option = 0;
		Scanner scanner = new Scanner(System.in);

		while(option <= 0 || option > 4) {
			atmMenu();

			try {
				option = scanner.nextInt();
			}
			catch(Exception ex) {
				System.out.println("The only valid options are: 1, 2, 3 and 4.");
				System.out.println("Press enter to try again.");
				scanner.nextLine();
			}

			switch(option) {
				case 1:
					checkFunds();
					break;
				case 2:
					addToSavings();
					break;
				case 3:
					retrieveMoney();
					break;
				case 4:
					System.out.println();
					System.out.println("Thank you for using the ATM!");
					System.exit(0);
			}

		}
	}
	
	private void checkFunds() {
		String funds = String.format("You have $%.2f left in your account.", atmService.checkSavings());
		System.out.println(funds);
		System.out.println();
		System.out.println("Press enter to continue.");
		new Scanner(System.in).nextLine();
		option = 0;
	}
	
	private void addToSavings() {
		double amount = 0;
		boolean errors = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the amount in $ you want to save: ");
		
		while(errors) {
			try {
				errors = false;
				amount = scanner.nextDouble();
				
			} catch (Exception ex) {
				errors = true;
				System.out.println("Enter the amount in the correct numeric format:");
				if(scanner.hasNextLine())
					scanner.nextLine();
			}
		}
		
		atmService.addAmount(amount);
		System.out.printf("You have saved $%.2f to your savings account.\n", amount);
		System.out.println();
		System.out.println("Press enter to continue.");
		scanner.nextLine();
		option = 0;
	}
	
	private void retrieveMoney() {
		TransactionResult result;
		double amount = 0;
		boolean errors = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the amount in $ you want to retrieve: ");

		while(errors) {
			try {
				errors = false;
				amount = scanner.nextDouble();

			} catch (Exception ex) {
				errors = true;
				System.out.println("Enter the amount in the correct numeric format:");
				if(scanner.hasNextLine())
					scanner.nextLine();
			}
		}
		
		result = atmService.retrieveAmount(amount);
		System.out.println(result.getResultMessage());
		System.out.println();
		System.out.println("Press enter to continue.");
		scanner.nextLine();
		option = 0;	
	}
	
	private void atmMenu() {
		System.out.println("=================================================");
		System.out.println("|                     ATM                       |");
		System.out.println("=================================================");
		
		System.out.println();
		System.out.println("Options:");
		System.out.println("\t1. Check your funds.");
		System.out.println("\t2. Add to your savings account.");
		System.out.println("\t3. Retrieve money.");
		System.out.println("\t4. Exit.");
		
		System.out.println();
		System.out.println("Enter your option: ");
	}
	
	private void logIn() {
		Scanner scanner = new Scanner(System.in);
		String username;
		String password;
		String option = "Y";
		
		
		while(option.equals("Y")) {

			System.out.println("=================================================");
			System.out.println("|                    Log in                     |");
			System.out.println("=================================================");
			System.out.println();
			System.out.println("Enter your username:");
			username = scanner.nextLine();
			System.out.println("Enter your password: ");
			password = scanner.nextLine();
			
			loggedIn = atmService.logIn(username, password);
			
			if(loggedIn) {
				option = "N";
			} else {
				System.out.println("Incorrect username or password.");
				System.out.println("Do you want to try again? Y/N");
				option = scanner.nextLine().toUpperCase();
				while(!option.equals("Y") && !option.equals("N")) {
					System.out.println("Do you want to try again? Y/N");
					option = scanner.nextLine().toUpperCase();
				}
			}
			
		}
		this.option = 0;
	}
	
	private void createAccount() {
		Scanner scanner = new Scanner(System.in);
		String username;
		String password;
		String name;
		String lastName;
		double amount = 0;
		boolean errors = true;
		

		
		System.out.println("=================================================");
		System.out.println("|               Account creation                |");
		System.out.println("=================================================");
		System.out.println();
		System.out.println("Enter your username:");
		username = scanner.nextLine();
		System.out.println("Enter your password: ");
		password = scanner.nextLine();
		System.out.println("Enter your name:");
		name = scanner.nextLine();
		System.out.println("Enter your last name:");
		lastName = scanner.nextLine();
		System.out.println("Enter the amount in $ you want to deposit to start the account (Ex: 100.0):");
		
		while(errors) {
			errors = false;
			try {
				amount = scanner.nextDouble();
			} catch(Exception ex) {
				if(scanner.hasNextLine())
					scanner.nextLine();
				errors = true;
				System.out.println("Please enter the amount in the correct format:");
			}
		}
		
		Customer cust = new Customer(username, password, name, lastName);
		Account acc = new Account(generatedId, amount);
		cust.setAccount(acc);
		repo.save(cust);
		
		System.out.println();
		System.out.println("Account created successfully! Press enter to continue.");
		scanner.nextLine();
		
		generatedId++;
		option = 0;
	}
	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
