package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.StringArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Visitor;
import com.firpy.repositories.impls.VisitDataAccess;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

public class QueryVisitorsByNameCommand extends Command
{
	public QueryVisitorsByNameCommand(VisitorDataAccess visitorDataAccess, VisitDataAccess visitDataAccess)
	{
		super("query-visitors-by-name-part", "Query a visitor by a name substring.");
		this.visitorDataAccess = visitorDataAccess;
		this.visitDataAccess = visitDataAccess;
		addArgumentSchemas();
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		String nameSubstring = nameSubstringArgumentSchema.parse(args);

		shell.println("Querying visitors by the name substring '%s'..".formatted(nameSubstring));
		shell.println("Result:");

		for (Visitor visitor : visitorDataAccess.findManyByPredicate(visitor -> visitor.getName().contains(nameSubstring)))
		{
			shell.println(visitor);
			shell.println("Visits:");
			visitDataAccess.findVisitsByVisitor(visitor).forEach(shell::println);
			shell.println();
		}
	}

	private final StringArgumentSchema nameSubstringArgumentSchema = new StringArgumentSchema("name-substring", "Name substring to query visitors by.", this);
	private final VisitorDataAccess visitorDataAccess;
	private final VisitDataAccess visitDataAccess;
}
