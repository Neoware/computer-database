package com.excilys.formation.cli;

public class ExitCommand implements Command {

	@Override
	public boolean execute() {
		System.out.println("Exiting computer-database CLI, bye!");
		return false;
	}
}
