package com.firpy.application.commands;

import com.firpy.application.commands.arguments.StringArgumentModel;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class RegisterMinorVisitorCommand extends Command
{
	public RegisterMinorVisitorCommand()
	{
		super("register-minor-visitor", "Registers a minor visitor to the system.");
		addArgumentModels(nameArgument);
	}

	@Override
	public void run(String @NotNull [] args, @NotNull Shell shell) throws CommandException
	{
		if (args.length != 1)
		{
			throw new CommandException("Invalid number of arguments.");
		}
		String name = nameArgument.parse(args[0]);
	}

	private StringArgumentModel nameArgument = new StringArgumentModel("name", "The name of the visitor");
}
