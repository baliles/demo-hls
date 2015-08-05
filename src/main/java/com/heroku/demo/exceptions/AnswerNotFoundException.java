package com.heroku.demo.exceptions;

public class AnswerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 262039464718205868L;

	public AnswerNotFoundException(Integer id) {
		super("Answer not found with id: " + id.toString());
	}

	public AnswerNotFoundException(String answer) {
		super("Answer " + answer + " not found!");
	}
}
