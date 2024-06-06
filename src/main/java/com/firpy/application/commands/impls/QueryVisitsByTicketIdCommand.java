package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.TicketId;
import com.firpy.repositories.impls.VisitDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

//Custom Command Three
public class QueryVisitsByTicketIdCommand extends Command
{
	public QueryVisitsByTicketIdCommand(VisitDataAccess visitDataAccess)
	{
		super("query-visits-by-ticket-id", "Queries visits by ticket id.");
		this.visitDataAccess = visitDataAccess;
		addArgumentSchemas(purchaseDateArgument, dailyTicketIdArgument);
	}
	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		LocalDate purchaseDate = purchaseDateArgument.parse(args);
		long dailyTicketId = dailyTicketIdArgument.parse(args);

		shell.println("Querying visits by ticket id...");
		shell.println("Result:");

		visitDataAccess.findVisitsByTicketId(new TicketId(purchaseDate, dailyTicketId))
		               .stream()
		               .map("%s%n"::formatted)
		               .forEach(shell::println);
	}

	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "The ticket's purchase date", this);
	private final LongArgumentSchema dailyTicketIdArgument = new LongArgumentSchema("daily-ticket-id", "The ticket's daily id", this);
	private final VisitDataAccess visitDataAccess;
}
