package com.excilys.formation.cli;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.mockito.Mock;

import com.excilys.formation.rest.RestClient;

public class CreateComputerCommandTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Mock
	RestClient restclient;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	public void testCreateComputerCommandWithEmptyName() {
		CreateComputerCommand command = new CreateComputerCommand();
		// System.setIn();
		command.execute(new Scanner(System.in));
		System.out.println("\n\n");
		assertEquals("You have pressed enter", outContent);
	}
}
