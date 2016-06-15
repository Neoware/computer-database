package com.excilys.formation.cli;

import org.springframework.stereotype.Component;

/**
 * Command to exit the CLI
 * 
 * @author neoware
 *
 */
@Component
public class ExitCommand implements Command {

	public ExitCommand() {
	}

	/**
	 * return false in order to stop the CLI loop
	 */
	@Override
	public boolean execute() {
		System.out.println("Exiting computer-database CLI, bye!");
		return false;
	}
}
