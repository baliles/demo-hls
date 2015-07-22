package com.heroku.demo.exceptions;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 262039464718205868L;

	public AccountNotFoundException(Integer id) {
		super("Account not found with id: " + id.toString());
	}

	public AccountNotFoundException(String account) {
		super("Account " + account + " not found!");
	}
}
