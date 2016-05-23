package com.excilys.formation.cli;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		System.out.println("Welcome in the computer database CLI client !");
		LOG.info("Entering new CLI session");
		Scanner scanner = new Scanner(System.in);
		CommandManager commandManager = new CommandManager();
		boolean shouldContinue = true;
		String commandName;
		while (shouldContinue == true) {
			System.out.print("computer-database>");
				scanner = new Scanner(System.in);
				commandName = scanner.nextLine();
				shouldContinue = commandManager.launchCommand(commandName);
			}
		scanner.close();
	}

}
