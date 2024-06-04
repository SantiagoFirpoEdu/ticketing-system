package com.firpy.application.commands.impls;
import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.repositories.exceptions.DailyTicketLimitReachedException;
import com.firpy.repositories.impls.TicketDataAccess;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;

import java.time.LocalDate;

public class EmitTicketCommand extends Command
{
	private final TicketDataAccess ticketDataAccess;
	private final VisitorDataAccess visitorDataAccess;

	public EmitTicketCommand(TicketDataAccess ticketDataAccess, VisitorDataAccess visitorDataAccess)
	{
		super("emit-ticket", "Emit a ticket for a visitor.");
		addArgumentSchemas(visitorIdArgument, purchaseDateArgument);
		this.ticketDataAccess = ticketDataAccess;
		this.visitorDataAccess = visitorDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
	private final LongArgumentSchema visitorIdArgument = new LongArgumentSchema("visitor-id", "Visitor ID to emit a ticket for.", this);
	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "Purchase date of the ticket.", this);
		if (args.length != 1) {
			throw new CommandUsageException("Usage: emit-ticket <visitor-id>",this);
		}

		Long visitorId;
		try {
			visitorId = Long.parseLong(args[0]);
		} catch (NumberFormatException e) {
			throw new CommandUsageException("Visitor ID must be a number.",this);
		}

		var visitor = visitorDataAccess.findVisitorById(visitorId);
		if (visitor.isEmpty()) {
			throw new CommandException("Visitor with ID " + visitorId + " not found.");
		}

		try {
			ticketDataAccess.registerTicket(LocalDate.now(), visitor.get());
			shell.println("Ticket emitted for visitor " + visitor.get().getName());
		} catch (DailyTicketLimitReachedException e) {
			shell.println("Daily ticket limit reached. Try again tomorrow.");
		}
	}


}
