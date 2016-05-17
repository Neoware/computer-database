package com.excilys.formation.main;

import java.sql.Connection;
import java.sql.Timestamp;

import com.excilys.formation.persistence.Computer;
import com.excilys.formation.persistence.ConnectionManager;
import com.excilys.formation.service.ComputerService;

public class App {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans computer database");
		ComputerService computerService = new ComputerService();
		computerService.showComputerDetails(5);
		Timestamp timestamp = new Timestamp(1463478577);
		Computer toCreate = new Computer("test", timestamp, timestamp, 5);
		computerService.createComputer(toCreate);
	}

}
