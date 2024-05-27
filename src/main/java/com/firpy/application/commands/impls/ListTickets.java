package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.shell.Shell;
import com.firpy.model.Attraction;
import com.firpy.model.Ticket;
import com.firpy.repositories.impls.TicketDataAccess;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListTickets extends Command
{
	public ListTickets(TicketDataAccess ticketDataAccess)
	{
		super("list-tickets", "Lists all tickets.");
		this.ticketDataAccess = ticketDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell)
	{
		List<Ticket> all = ticketDataAccess.findAllTickets();
		shell.println("Tickets:");
		all.stream()
		   .map(ticket -> "%s%n".formatted(ticket.toString()))
		   .forEach(shell::println);
	}

	private final TicketDataAccess ticketDataAccess;
}
