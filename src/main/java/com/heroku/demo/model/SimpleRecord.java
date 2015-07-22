package com.heroku.demo.model;

import java.util.HashMap;
import java.util.Map;


public class SimpleRecord {

	private String key;
	private String value;
	
	public SimpleRecord() {
		super();
	}

	public SimpleRecord(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Map<String, String> set(String key, String value) {
		this.key =key;
		this.value = value;
		return getRecord();
	}
	
	public Map<String, String> set(String key, Double value) {
		this.key = key;
		this.value = value.toString();
		return getRecord();
	}
	
	public Map<String, String> set(String key, Integer value) {
		this.key = key;
		this.value = value.toString();
		return getRecord();
	}

	public Map<String, String> getRecord() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(this.key, this.value);		
		return map;
	}
}
