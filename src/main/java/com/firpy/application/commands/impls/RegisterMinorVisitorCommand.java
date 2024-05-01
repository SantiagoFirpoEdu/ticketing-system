package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.StringArgumentModel;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.Shell;
import com.firpy.model.MinorVisitor;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

public class RegisterMinorVisitorCommand extends Command
{
	public RegisterMinorVisitorCommand(CrudRepository<MinorVisitor, Long> repository)
	{
		super("register-minor-visitor", "Registers a minor visitor to the system.");
		this.repository = repository;
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

	private final StringArgumentModel nameArgument = new StringArgumentModel("name", "The name of the visitor");
	private final CrudRepository<MinorVisitor, Long> repository;
}
