package com.excilys.formation.cli;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("Welcome in the computer database CLI client !");
		//LOG.info("Entering new CLI session");
		Scanner scanner = new Scanner(System.in);
		CommandManager commandManager = new CommandManager();
		boolean shouldContinue = true;
		String commandName;
		while (shouldContinue == true) {
			System.out.print("computer-database>");
			commandName = scanner.nextLine();
			shouldContinue = commandManager.launchCommand(commandName);
		}
		scanner.close();
	}

}
