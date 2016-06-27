package com.excilys.formation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class allowing to have succes or failure information with some explanations.
 * 
 * @author neoware
 *
 */
public class ReturnInformation {
	private boolean success;
	private List<String> messages;

	/**
	 * Setting true by default for success -> Optimistic init
	 */
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
		this.messages = messages;
	}

}
