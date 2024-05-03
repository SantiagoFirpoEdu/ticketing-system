package com.firpy.application.shell;

import com.firpy.application.commands.*;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandNotFoundException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.commands.impls.*;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Shell
{
	public Shell(VisitorDataAccess visitorDataAccess)
	{
		commandRegistry.registerCommand(new RegisterVisitorCommand(visitorDataAccess));
		commandRegistry.registerCommand(new RegisterMinorVisitorCommand(visitorDataAccess));
		commandRegistry.registerCommand(new ListVisitors(visitorDataAccess));
		commandRegistry.registerCommand(new ExitCommand());
		commandRegistry.registerCommand(new HelpCommand());
	}

	public void exit()
	{
		shouldExit = true;
	}

	public void println(String message)
	{
		System.out.println(message);
	}

	public void println()
	{
		System.out.println();
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
			System.out.printf(ShellColors.RED + "%nCommand not found: %s%n", e.getMessage() + ShellColors.RESET);
		}
		catch (CommandException | CommandUsageException e)
		{
			System.out.printf(ShellColors.RED + "%nError running command: %s%n", e.getMessage() + ShellColors.RESET);
		}
    }

	private final CommandRegistry commandRegistry = new CommandRegistry();
	private boolean shouldExit = false;
}
