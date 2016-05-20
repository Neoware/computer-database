package com.excilys.formation.cli;

import java.util.Scanner;

import com.excilys.formation.command.Command;

public class App {

	public static void main(String[] args) {
		System.out.println("Welcome in the computer database CLI client !");
		Scanner scanner = new Scanner(System.in);
		Command command = new Command();
		Boolean shouldContinue = new Boolean(true);
		while (shouldContinue == true){
			System.out.print("computer-database>");
				String input = scanner.nextLine();
				command.Dispatch(input);
		}
		scanner.close();
	}

}
