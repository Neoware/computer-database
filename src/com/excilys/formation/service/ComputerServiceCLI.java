package com.excilys.formation.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.model.Page;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.Computer;
import com.excilys.formation.persistence.ComputerDAO;

public class ComputerServiceCLI implements ComputerService{

	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	private Scanner scanner;

	public ComputerServiceCLI(){
		computerDAO = new ComputerDAO();
		companyDAO = new CompanyDAO();
		scanner = new Scanner(System.in);
	}

	public void listAllComputers(){
		List <Computer> computers = computerDAO.getAll();

		Page <Computer> pages = new Page<Computer>(computers);

		String navigation;
		while (true){
			pages.printPage();
			System.out.println("n for next page, p for previous, a to abort and go back to shell");
			if (scanner.hasNextLine())
			{
				navigation = scanner.nextLine();
				if (navigation.equals("n"))
					pages.next();
				else if (navigation.equals("p"))
					pages.previous();
				else if (navigation.equals("a"))
					return;
			}
		}
	}

	public void showComputerDetails(){
		System.out.print("What is the id of the computer you want details about ?");
		if (scanner.hasNextInt())
		{
			int id = scanner.nextInt();
			Computer temp = computerDAO.find(id);
			if (temp != null)
				System.out.println(temp);
			else
				System.out.println("Computer with this id doesn\'t exist");
		}
		else
			System.out.println("An integer need to be submitted");
	}

	public void createComputer(){
		Computer toCreate = new Computer();
		String name;
		do
		{	
			System.out.println("Choose a name for your computer (mandatory)");
			name = scanner.nextLine();
			System.out.println("You have pressed enter");
		} while (name.equals(""));
		toCreate.setName(name);
		System.out.println("Choose a timestamp introduced for your computer (not mandatory)");
		String introduced = scanner.nextLine();
		Timestamp introducedTimestamp = getTimestampFromString(introduced);
		if (introducedTimestamp == null)
			System.out.println("Bad timestamp format setting to default");
		else
			toCreate.setIntroduced(introducedTimestamp);
		System.out.println("Choose a timestamp discontinued for your computer (not mandatory)");
		String discontinued = scanner.nextLine();
		Timestamp discontinuedTimestamp = getTimestampFromString(discontinued);
		if (discontinuedTimestamp == null)
			System.out.println("Bad timestamp format setting to default");
		else
			toCreate.setDiscontinued(introducedTimestamp);
		System.out.println("Choose the id of the manufacturer of the computer (not mandatory)");
		if (scanner.hasNextInt()){
			int companyId = scanner.nextInt();
			if (companyDAO.find(companyId) != null)
				toCreate.setCompanyId(companyId);
			else{
				System.out.println("No such company");
			}
		}
		scanner.nextLine();
		System.out.println(toCreate);
		computerDAO.create(toCreate);
	}

	public void updateComputer(){
		System.out.print("What is the id of the computer you want to update ?");
		while (!scanner.hasNextInt()){
			if (scanner.next() != "")
				System.out.println("You need to provide a correct id");
			scanner.nextLine();	
		}
		int id = scanner.nextInt();
		scanner.nextLine();
		Computer toUpdate = computerDAO.find(id);
		if (toUpdate != null)
		{
			System.out.println("Change the name of the computer ? (current: " + toUpdate.getName() + ")");
			if (scanner.hasNext())
				toUpdate.setName(scanner.nextLine());
			else
			{
				System.out.println("Name is mandatory");
				return;
			}
			System.out.println("Change the introduced date of the computer ? (current: " + toUpdate.getIntroduced() + ")");
			if (scanner.hasNextLine())
			{
				String introduced = scanner.nextLine();
				Timestamp introducedTimestamp = getTimestampFromString(introduced);
				if (introducedTimestamp != null)
					toUpdate.setIntroduced(introducedTimestamp);
				else
					System.out.println("Bad timestamp");
			}
			System.out.println("Change the discontinued date of the computer ? (current: " + toUpdate.getDiscontinued() + ")");
			if (scanner.hasNextLine())
			{
				String discontinued = scanner.nextLine();
				Timestamp discontinuedTimestamp = getTimestampFromString(discontinued);
				if (discontinuedTimestamp != null)
					toUpdate.setDiscontinued(discontinuedTimestamp);
				else
					System.out.println("Bad timestamp");
			}
			System.out.println("Change the company id of the computer ? (current: " + toUpdate.getCompanyId() + ")");
			if (scanner.hasNextInt())
			{
				int companyId = scanner.nextInt();
				System.out.println(companyId);
				if (companyDAO.find(companyId) != null)
					toUpdate.setCompanyId(companyId);
				else
				{
					System.out.println("No such company");
				}
			}
			computerDAO.update(toUpdate);
			System.out.println("Success");
		}
		else
			System.out.println("Computer with this id doesn\'t exist");
	}

	public void deleteComputer(){
		System.out.print("What is the id of the computer you want to delete ?");
		if (scanner.hasNextInt())
		{
			int id = scanner.nextInt();
			scanner.nextLine();
			Computer temp = computerDAO.find(id);
			if (temp != null)
			{
				System.out.println("Deleting " + temp + " ...");
				computerDAO.delete(temp.getId());
				System.out.println("Success");
			}
			else
				System.out.println("Computer with this id doesn\'t exist");
		}
		else
		{
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
	}

	public static Timestamp getTimestampFromString(String inputString)
	{ 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			java.util.Date date = format.parse(inputString);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		}
		catch(ParseException e)
		{
			return null;
		}
	}
}