package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.model.Visitor;
import com.firpy.repositories.impls.TicketDataAccess;
import com.firpy.repositories.impls.VisitDataAccess;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Optional;

//Custom Command Two
public class QueryVisitorByTicketIdCommand extends Command
{
	public QueryVisitorByTicketIdCommand(TicketDataAccess ticketDataAccess, VisitDataAccess visitDataAccess)
	{
		super("query-visitor-by-ticket-id", "Query a visitor by a ticket id.");
		this.ticketDataAccess = ticketDataAccess;
		this.visitDataAccess = visitDataAccess;
		addArgumentSchemas(purchaseDateArgument, dailyTicketIdArgument);
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		LocalDate purchaseDate = purchaseDateArgument.parse(args);
		long dailyTicketId = dailyTicketIdArgument.parse(args);

		shell.println("Querying visitor by ticket id...");
		shell.println("Result:");
		Optional<Ticket> result = ticketDataAccess.findById(new TicketId(purchaseDate, dailyTicketId));
		if (result.isPresent())
		{
			Visitor visitor = result.get().visitor();
			shell.println(visitor);
			shell.println("Visits:");
			visitDataAccess.findVisitsByVisitor(visitor).forEach(shell::println);
		}
		else
		{
			shell.println("No ticket with the daily id %d purchased on %s was found.".formatted(dailyTicketId, purchaseDate));
		}
	}

	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "The ticket's purchase date", this);
	private final LongArgumentSchema dailyTicketIdArgument = new LongArgumentSchema("daily-ticket-id", "The ticket's daily id", this);
	private final TicketDataAccess ticketDataAccess;
	private final VisitDataAccess visitDataAccess;
}
