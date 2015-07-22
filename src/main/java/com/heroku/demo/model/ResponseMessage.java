package com.heroku.demo.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {

	private String message = "";
	private Boolean success = false;
	private Integer code = 0;
	private String metadata;
	private Object data;

	public ResponseMessage(String message, Boolean success, Object data) {
		super();
		this.message = message;
		this.success = success;
		this.data = data;
		this.metadata = "";
		this.code = 0;
	}

	public ResponseMessage() {
		super();
		this.message = "";
		this.success = true;
		this.code = 0;
		this.metadata = "";
	}
	
	public void setError(Integer code, String message) {
		this.code = code;
		this.metadata = "";
		this.message = message;
		this.success = false;
		this.data = "";
	}
	
	@XmlAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlAttribute
	public Boolean getSuccess() {
		return success;
	}


	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@XmlAttribute
	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
	@XmlAttribute
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	@XmlAttribute
	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
		
}
