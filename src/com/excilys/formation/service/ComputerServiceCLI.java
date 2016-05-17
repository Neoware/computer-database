package com.excilys.formation.service;

import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
		for (Computer computer : computers){
			System.out.println(computer);
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
		computerDAO.create(toCreate);
	}

	public void updateComputer(){
		System.out.print("What is the id of the computer you want to update ?");
		int id = scanner.nextInt();
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
			System.out.println("An integer need to be submitted");
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