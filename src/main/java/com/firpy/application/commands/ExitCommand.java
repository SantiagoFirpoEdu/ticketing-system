package com.firpy.application.commands;

import org.jetbrains.annotations.NotNull;

public class ExitCommand extends Command
{
	protected ExitCommand()
	{
		super("exit", "Exits the shell");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException
	{
		shell.exit();
		shell.println("Exiting...");
	}
}
