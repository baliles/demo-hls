package com.heroku.demo.exceptions;

public class SectionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 262039464718205868L;

	public SectionNotFoundException(Integer id) {
		super("Section not found with id: " + id.toString());
	}

	public SectionNotFoundException(String section) {
		super("Section " + section + " not found!");
	}
}
