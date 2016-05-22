package com.excilys.formation.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.command.Page;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;

import jdk.nashorn.internal.ir.ContinueNode;

public class ListComputers implements Command {
	private static ComputerService computerService;

	public ListComputers() {
		computerService = ComputerService.getInstance();
	}

	@Override
	public boolean execute() {
		List<ComputerDTO> computers = new ArrayList<>();
		Page<Computer> pages = new Page<Computer>(computerService);
		Scanner scanner = new Scanner(System.in);
		String navigation;
		boolean shouldContinue = true;
		boolean refresh = true;
		while (shouldContinue == true) {
			System.out.println();
			System.out.println("n for next page, p for previous, a to abort and go back to shell");
			if (scanner.hasNextLine()) {
				if (refresh == true){
					System.out.println(computers);
					refresh = false;
				}
				navigation = scanner.nextLine();
				if (navigation.equals("n")){
					 if (pages.next() == true){
						 computers = pages.getCurrentPageElements();
						 refresh = true;
					 }
				}			 
				else if (navigation.equals("p")){
					 if (pages.previous() == true){
						 computers = pages.getCurrentPageElements();
						 refresh = true;
					 }
				}	
				else if (navigation.equals("a"))
					shouldContinue = false;
			}
		}
		return true;
	}

}
