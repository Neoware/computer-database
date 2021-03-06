package com.excilys.formation.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is responsible to populate the list of command that the CLI can
 * execute, and to execute them.
 * 
 * @author neoware
 *
 */
@Component
public class CommandManager {
	private static final Logger LOG = LoggerFactory.getLogger(CommandManager.class);
	private static Map<String, Command> commands;
	@Autowired
	private ListComputersCommand listComputersCommand;
	@Autowired
	private ListCompaniesCommand listCompaniesCommand;
	@Autowired
	private ShowOneComputerCommand showOneComputerCommand;
	@Autowired
	private UpdateComputerCommand updateComputerCommand;
	@Autowired
	private DeleteComputerCommand deleteComputerCommand;
	@Autowired
	private DeleteCompanyCommand deleteCompanyCommand;
	@Autowired
	private ExitCommand exitCommand;
	@Autowired
	private CreateComputerCommand createComputerCommand;

	public CommandManager() {

	}

	public void populate() {
		commands = new HashMap<String, Command>();
		commands.put("list-computers", listComputersCommand);
		commands.put("list-companies", listCompaniesCommand);
		commands.put("computer", showOneComputerCommand);
		commands.put("create", createComputerCommand);
		commands.put("update", updateComputerCommand);
		commands.put("delete", deleteComputerCommand);
		commands.put("delete-company", deleteCompanyCommand);
		commands.put("exit", exitCommand);
	}

	/**
	 * Add a command to the map containing all commands.
	 * 
	 * @param commandName
	 *            the name of the command, what the user is supposed to enter in
	 *            the CLI to execute the command.
	 * @param toExecute
	 *            the Command class with an execute method that is going to be
	 *            triggered when the command is called in the CLI.
	 */
	public void addCommand(String commandName, Command toExecute) {
		commands.put(commandName, toExecute);
	}

	/**
	 * 
	 * @param commandName
	 *            the input from the user that should correspond to a key in the
	 *            command map.
	 * @return true if the cli should continue, false otherwise.
	 */
	public boolean launchCommand(String commandName, Scanner scanner) {
		Command command = commands.get(commandName);
		boolean shouldContinue = true;
		if (command != null) {
			LOG.info("Excecuting command : " + commandName);
			shouldContinue = command.execute(scanner);
		} else {
			System.out.println("The command " + commandName + " doesn't exist.");
			LOG.info("Invalid command : " + commandName);
		}
		return shouldContinue;
	}

	public static Map<String, Command> getCommands() {
		return commands;
	}

	public static void setCommands(Map<String, Command> commands) {
		CommandManager.commands = commands;
	}

}
