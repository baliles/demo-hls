package com.heroku.demo.exceptions;

public class QuestionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 262039464718205868L;

	public QuestionNotFoundException(Integer id) {
		super("Question not found with id: " + id.toString());
	}

	public QuestionNotFoundException(String question) {
		super("Question " + question + " not found!");
	}
}
