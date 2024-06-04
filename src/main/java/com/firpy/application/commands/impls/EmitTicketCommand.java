package com.firpy.application.commands.impls;
import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Visitor;
import com.firpy.repositories.exceptions.DailyTicketLimitReachedException;
import com.firpy.repositories.impls.TicketDataAccess;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class EmitTicketCommand extends Command
{
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
		long visitorId = visitorIdArgument.parse(args);
		LocalDate ticketDate = purchaseDateArgument.parse(args);

		Optional<Visitor> visitor = visitorDataAccess.findVisitorById(visitorId);
		if (visitor.isEmpty())
		{
			throw new CommandException("Visitor with ID " + visitorId + " not found.");
		}

		try
		{
			ticketDataAccess.registerTicket(ticketDate, visitor.get());
			shell.println("Ticket emitted for visitor %s".formatted(visitor.get().getName()));
		}
		catch (DailyTicketLimitReachedException e)
		{
			throw new CommandException(e.getMessage());
		}
	}

	private final TicketDataAccess ticketDataAccess;
	private final VisitorDataAccess visitorDataAccess;
	private final LongArgumentSchema visitorIdArgument = new LongArgumentSchema("visitor-id", "Visitor ID to emit a ticket for.", this);
	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "Purchase date of the ticket.", this);
}
