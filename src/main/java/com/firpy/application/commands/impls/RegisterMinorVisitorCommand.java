package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.arguments.impls.StringArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.shell.Shell;
import com.firpy.model.MinorVisitor;
import com.firpy.repositories.exceptions.CheckedIllegalArgumentException;
import com.firpy.repositories.impls.MinorVisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class RegisterMinorVisitorCommand extends Command
{
	public RegisterMinorVisitorCommand(MinorVisitorDataAccess minorVisitorDataAccess)
	{
		super("register-minor-visitor", "Registers a minor visitor to the system.");
		this.minorVisitorDataAccess = minorVisitorDataAccess;
		addArgumentModels(nameArgument, guardianIdArgument, dateOfBirthArgument);
	}

	@Override
	public void run(String @NotNull [] args, @NotNull Shell shell) throws CommandException
	{
		String name = nameArgument.parse(args);
		Long guardianId = guardianIdArgument.parse(args);
		LocalDate dateOfBirth = dateOfBirthArgument.parse(args);
        try
        {
            MinorVisitor minorVisitor = minorVisitorDataAccess.registerMinorVisitor(name, dateOfBirth, guardianId);
			shell.println("Minor visitor registered successfully: %s".formatted(minorVisitor));
        }
        catch (CheckedIllegalArgumentException e)
        {
			throw new CommandException(e.getMessage());
        }
    }

	private final StringArgumentSchema nameArgument = new StringArgumentSchema("name", "The name of the minor visitor", this);
	private final LongArgumentSchema guardianIdArgument = new LongArgumentSchema("guardian-id", "The id of the minor visitor's guardian", this);
	private final LocalDateArgumentSchema dateOfBirthArgument = new LocalDateArgumentSchema("date-of-birth", "The date of birth of the minor visitor", this);
	private final MinorVisitorDataAccess minorVisitorDataAccess;
}
