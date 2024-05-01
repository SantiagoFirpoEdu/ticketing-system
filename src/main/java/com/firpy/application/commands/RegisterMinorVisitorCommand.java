package com.firpy.application.commands;

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
		String name = nameArgument.parse(args[0]);
	}

	private StringArgumentModel nameArgument = new StringArgumentModel("name", "The name of the visitor");
}
