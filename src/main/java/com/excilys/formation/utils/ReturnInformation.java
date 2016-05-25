package com.excilys.formation.utils;

public class ReturnInformation {
	private boolean success;
	private StringBuffer message;

	public ReturnInformation() {
		success = true;
		message = new StringBuffer();
	}

	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}
}
