package com.firpy.application.shell;

import com.firpy.application.commands.*;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandNotFoundException;
import com.firpy.model.MinorVisitor;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Shell
{
	public Shell(CrudRepository<MinorVisitor, Long> minorVisitorRepository, CrudRepository<Visitor, Long> visitorRepository)
	{
		commandRegistry.registerCommand(new RegisterVisitorCommand(visitorRepository));
		commandRegistry.registerCommand(new RegisterMinorVisitorCommand(minorVisitorRepository));
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
			System.out.printf(ShellColors.RED + "%nCommand not found: %s%n", e.getMessage() + ShellColors.RESET);
		}
		catch (CommandException e)
		{
			System.out.printf(ShellColors.RED + "%nError running command: %s%n", e.getMessage() + ShellColors.RESET);
		}
	}

	private final CommandRegistry commandRegistry = new CommandRegistry();
	private boolean shouldExit = false;
}
