package com.danielc.entities;

import java.math.BigDecimal;

public class Account {
	private static long generatedId = 1;
	
	private long id;
	private long customerId;
	private BigDecimal savings;
	
	public Account(long customerId) {
		this.customerId = customerId;
		this.id = generatedId;
		this.savings = BigDecimal.valueOf(500.0);
	}
	
	public Account(long customerId, double amount) {
		this.customerId = customerId;
		this.id = generatedId;
		this.savings = BigDecimal.valueOf(amount);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getSavings() {
		return savings;
	}

	public void setSavings(BigDecimal savings) {
		this.savings = savings;
	}
	
	public void addToSavings(double amount) {
		savings = savings.add(BigDecimal.valueOf(amount));
	}
	
	public double retrieveFromSavings(double amount) {
		savings = savings.subtract(BigDecimal.valueOf(amount));
		return amount;
	}
	
	public double getTotalSavings() {
		return savings.doubleValue();
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", savings="+getTotalSavings()+ "]";
	}
	
	
}
