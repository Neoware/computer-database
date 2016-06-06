package com.excilys.formation.cli;

/**
 * Command to exit the CLI
 * 
 * @author neoware
 *
 */
public class ExitCommand implements Command {

	/**
	 * return false in order to stop the CLI loop
	 */
	@Override
	public boolean execute() {
		System.out.println("Exiting computer-database CLI, bye!");
		return false;
	}
}
