package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.StringArgumentModel;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class RegisterVisitorCommand extends Command
{
	public RegisterVisitorCommand(CrudRepository<Visitor, Long> repository)
	{
		super("register-visitor", "Registers a visitor to the system.");
		this.repository = repository;
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

		Visitor visitor = new Visitor(0, name, new Date(), phoneNumberValue);
		repository.save(visitor);
		shell.println("Visitor registered successfully: %s".formatted(visitor));
	}

	private final StringArgumentModel nameArgument = new StringArgumentModel("name", "The name of the visitor");
	private final StringArgumentModel phoneNumber = new StringArgumentModel("phoneNumber", "The phone number of the visitor");

	private final CrudRepository<Visitor, Long> repository;
}
