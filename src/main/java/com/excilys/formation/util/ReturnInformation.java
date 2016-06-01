package com.excilys.formation.util;

import java.util.ArrayList;
import java.util.List;

public class ReturnInformation {
	private boolean success;
	private List<String> messages;

	public ReturnInformation() {
		success = true;
		messages = new ArrayList<>();
	}

	public void addMessage(String errorMessage) {
		messages.add(errorMessage);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		messages = messages;
	}

}
