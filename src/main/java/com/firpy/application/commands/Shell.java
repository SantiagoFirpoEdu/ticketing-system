package com.firpy.application.commands;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Shell
{
	public Shell()
	{
		commandRegistry.registerCommand(new RegisterVisitorCommand());
		commandRegistry.registerCommand(new RegisterMinorVisitorCommand());
		commandRegistry.registerCommand(new ExitCommand());
	}

	public void exit()
	{
		shouldExit = true;
	}

	public void println(String message)
	{
		System.out.println(message);
	}

	public void waitForInput()
	{
		while (!shouldExit)
		{
			System.out.print(">> ");
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();
			execute(command);
		}
	}

	public void help()
	{
		System.out.println(commandRegistry.prettyPrint());
	}

	public void execute(@NotNull String command)
	{
		String[] tokens = command.split(" ");

		if (tokens.length == 0)
		{
			System.err.println("No command provided");
		}

		try
		{
			commandRegistry.run(tokens[0], tokens.length > 1 ? Arrays.stream(tokens).skip(1).toArray(String[]::new) : new String[0], this);
		}
		catch (CommandNotFoundException e)
		{
			System.err.printf("Command not found: %s%n", e.getMessage());
		}
		catch (CommandException e)
		{
			System.err.printf("Error running command: %s%n", e.getMessage());
		}
	}

	private final CommandRegistry commandRegistry = new CommandRegistry();
	private boolean shouldExit = false;
}
