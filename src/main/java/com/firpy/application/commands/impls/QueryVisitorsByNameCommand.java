package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.StringArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

public class QueryVisitorsByNameCommand extends Command
{
	public QueryVisitorsByNameCommand(VisitorDataAccess visitorDataAccess)
	{
		super("query-visitors-by-name-part", "Query a visitor by a name substring.");
		this.visitorDataAccess = visitorDataAccess;
		addArgumentSchemas();
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		String nameSubstring = nameSubstringArgumentSchema.parse(args);

		shell.println("Querying visitors by the name substring '%s'..".formatted(nameSubstring));
		shell.println("Result:");

		visitorDataAccess.findManyByPredicate(visitor -> visitor.getName().contains(nameSubstring))
						 .forEach(shell::println);
	}

	private final StringArgumentSchema nameSubstringArgumentSchema = new StringArgumentSchema("name-substring", "Name substring to query visitors by.", this);
	private final VisitorDataAccess visitorDataAccess;
}
