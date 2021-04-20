package com.danielc.services.utils;

public class TransactionResult {
	private final boolean success;
	private final String resultMessage;
	private final double amount;
	
	public TransactionResult(boolean success, String resultMessage, double amount) {
		super();
		this.success = success;
		this.resultMessage = resultMessage;
		this.amount = amount;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public double getAmount() {
		return amount;
	}
	
	
}
