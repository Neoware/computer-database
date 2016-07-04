package com.excilys.formation.cli;

import java.util.Scanner;

/**
 * 
 * Command with an execute method corresponding to the action of the command.
 * 
 * @author Neoware
 * 
 */
public interface Command {
	/**
	 * The method that corresponds to the action of the command.
	 * 
	 * @return true if the cli should continue, or false otherwise.
	 */
	public boolean execute(Scanner scanner);
}
