package com.excilys.formation.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {

	private static Map<String, Command> commands;

	public CommandManager() {
		commands = new HashMap<String, Command>();
		commands.put("list-computers", new ListComputers());
	}

	public void addCommand(String commandName, Command toExecute) {
		commands.put(commandName, toExecute);
	}

	public static Map<String, Command> getCommands() {
		return commands;
	}

	public static void setCommands(Map<String, Command> commands) {
		CommandManager.commands = commands;
	}

	public boolean launchCommand(String commandName) {
		Command command = commands.get(commandName);
		boolean shouldContinue = true;
		if (command != null) {
			// Log.info("Excecuting command : " + commandName);
			shouldContinue = command.execute();
		} else {
			System.out.println("The command " + commandName + " doesn't exist.");
			// Log.info("Invalid command : " + commandName);
		}
		return shouldContinue;
	}

}
