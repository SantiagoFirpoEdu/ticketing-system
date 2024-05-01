package com.firpy.application.commands;

import com.firpy.application.commands.arguments.StringArgumentModel;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class RegisterVisitorCommand extends Command
{
	public RegisterVisitorCommand()
	{
		super("register-visitor", "Registers a visitor to the system.");
		addArgumentModels(nameArgument, phoneNumber);
	}

	@Override
	public void run(String @NotNull [] args, @NotNull Shell shell) throws CommandException
	{
		if (args.length != 2)
		{
			throw new CommandException("Invalid number of arguments.");
		}

		String name = nameArgument.parse(args[0]);
		String phoneNumberValue = phoneNumber.parse(args[1]);
	}

	private StringArgumentModel nameArgument = new StringArgumentModel("name", "The name of the visitor");
	private StringArgumentModel phoneNumber = new StringArgumentModel("phoneNumber", "The phone number of the visitor");
}
