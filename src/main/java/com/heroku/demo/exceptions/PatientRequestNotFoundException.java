package com.heroku.demo.exceptions;

public class PatientRequestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 262039464718205868L;

	public PatientRequestNotFoundException(Integer id) {
		super("Account not found with id: " + id.toString());
	}

	public PatientRequestNotFoundException(String account) {
		super("Account " + account + " not found!");
	}
}
