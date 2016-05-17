package com.excilys.formation.main;

import java.util.Scanner;

import com.excilys.formation.dispatcher.CommandDispatcher;

public class App {

	public static void main(String[] args) {
		System.out.println("Welcome in the computer database CLI client !");
		Scanner scanner = new Scanner(System.in);
		CommandDispatcher commandDispatcher = new CommandDispatcher();
		while (true){
			System.out.print("computer-database>");
			String input = scanner.nextLine();
			commandDispatcher.Dispatch(input);
		}
	}

}
