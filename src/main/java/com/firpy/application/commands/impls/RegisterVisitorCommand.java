package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.StringArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.AdultVisitor;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class RegisterVisitorCommand extends Command
{
	public RegisterVisitorCommand(VisitorDataAccess repository)
	{
		super("register-visitor", "Registers a visitor to the system.");
		this.repository = repository;
		addArgumentSchemas(nameArgument, dateOfBirthArgument, phoneNumberArgument);
	}

	@Override
	public void run(String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		String name = nameArgument.parse(args);
		LocalDate dateOfBirth = dateOfBirthArgument.parse(args);
		String phoneNumber = phoneNumberArgument.parse(args);

		AdultVisitor adultVisitor = repository.registerAdultVisitor(name, dateOfBirth, phoneNumber);
		shell.println("Visitor registered successfully: %s".formatted(adultVisitor));
	}

	private final StringArgumentSchema nameArgument = new StringArgumentSchema("name", "The name of the visitor", this);
	private final LocalDateArgumentSchema dateOfBirthArgument = new LocalDateArgumentSchema("dateOfBirth", "The date of birth of the visitor", this);
	private final StringArgumentSchema phoneNumberArgument = new StringArgumentSchema("phoneNumber", "The phone number of the visitor", this);
	private final VisitorDataAccess repository;
}
