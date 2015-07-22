package com.heroku.demo.model;

import java.io.Serializable;

public class Register implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627073529234315838L;

	public Register(){
		super();
	}
	
	public String type;
	public String comment;
	public String originId;
	public String patient;
	public String subCategory;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
