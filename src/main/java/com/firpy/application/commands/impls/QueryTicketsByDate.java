package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.repositories.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class QueryTicketsByDate extends Command
{
	public QueryTicketsByDate(CrudRepository<Ticket, TicketId> ticketRepository)
	{
		super("query-tickets-by-date", "query tickets by date.");
		this.ticketRepository = ticketRepository;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		LocalDate parsedDate = filterByDateArgument.parse(args);
		List<Ticket> filtered = ticketRepository.findManyByPredicate(ticket -> ticket.id().purchaseDate().isEqual(parsedDate));

		shell.println("Result:");
		filtered.forEach(shell::println);
	}

	private final CrudRepository<Ticket, TicketId> ticketRepository;
	private final LocalDateArgumentSchema filterByDateArgument = new LocalDateArgumentSchema("filter-by-date", "The date used to filter visits", this);
}
