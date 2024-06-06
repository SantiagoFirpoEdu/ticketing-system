package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.IntArgumentSchema;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

public class QueryEarningsForYearCommand extends Command
{
	public QueryEarningsForYearCommand(CrudRepository<Ticket, TicketId> ticketRepository)
	{
		super("query-earnings-for-year", "Query earnings for a year.");
		this.ticketRepository = ticketRepository;
		addArgumentSchemas(yearArgument);
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandUsageException
	{
		int year = yearArgument.parse(args);
			shell.println("Querying earnings for %d..".formatted(year));

		int totalEarnings = ticketRepository.findManyByPredicate(ticket -> ticket.id().purchaseDate().getYear() == year)
											.stream()
											.mapToInt(ticket -> ticket.visitor().getTicketCost())
											.sum();

			shell.println("Total earnings for %d: %d".formatted(year, totalEarnings));
	}

	private final IntArgumentSchema yearArgument = new IntArgumentSchema("year", "Year to query earnings for.", this);

	private final CrudRepository<Ticket, TicketId> ticketRepository;
}
