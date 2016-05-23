package com.excilys.formation.cli;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandManager {
	private static final Logger LOG = LoggerFactory.getLogger(CommandManager.class);
	private static Map<String, Command> commands;
	

	public CommandManager() {
		commands = new HashMap<String, Command>();
		commands.put("list-computers", new ListComputersCommand());
		commands.put("list-companies", new ListCompaniesCommand());
		commands.put("computer", new ShowOneComputerCommand());
		commands.put("create", new CreateComputerCommand());
		commands.put("update", new UpdateComputerCommand());
		commands.put("delete", new DeleteComputerCommand());
		commands.put("exit", new ExitCommand());
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
			LOG.info("Excecuting command : " + commandName);
			shouldContinue = command.execute();
		} else {
			System.out.println("The command " + commandName + " doesn't exist.");
			LOG.info("Invalid command : " + commandName);
		}
		return shouldContinue;
	}

}
