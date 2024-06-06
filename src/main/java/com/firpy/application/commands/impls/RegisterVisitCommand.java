package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.model.Attraction;
import com.firpy.model.Ticket;
import com.firpy.model.TicketId;
import com.firpy.model.Visit;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.impls.TicketDataAccess;
import com.firpy.repositories.impls.VisitDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Optional;

public class RegisterVisitCommand extends Command
{

	public RegisterVisitCommand(VisitDataAccess visitDataAccess, TicketDataAccess ticketDataAccess, CrudRepository<Attraction, Long> attractionRepository)
	{
		super("register-visit", "Register a visit.");
		this.visitDataAccess = visitDataAccess;
		this.ticketDataAccess = ticketDataAccess;
		this.attractionRepository = attractionRepository;
		addArgumentSchemas(purchaseDateArgument, dailyIdArgument, attractionIdArgument);
	}

	@Override
	public void run(@NotNull String @NotNull [] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		LocalDate purchaseDate = purchaseDateArgument.parse(args);
		long dailyId = dailyIdArgument.parse(args);
		TicketId ticketId = new TicketId(purchaseDate, dailyId);

		Optional<Ticket> optionalTicket = ticketDataAccess.findById(ticketId);
		if (optionalTicket.isEmpty())
		{
			throw new CommandException("Ticket not found: %s".formatted(ticketId));
		}
		Ticket ticket = optionalTicket.get();

		long attractionId = attractionIdArgument.parse(args);
		Optional<Attraction> optionalAttraction = attractionRepository.findById(attractionId);
		if (optionalAttraction.isEmpty())
		{
			throw new CommandException("Attraction not found: %d".formatted(attractionId));
		}
		Attraction attraction = optionalAttraction.get();

		Visit visit = visitDataAccess.registerVisit(ticket, attraction);
		shell.println("Visit registered successfully: %s".formatted(visit));
	}

	private final VisitDataAccess visitDataAccess;
	private final TicketDataAccess ticketDataAccess;
	private final CrudRepository<Attraction, Long> attractionRepository;
	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "The date the ticket was purchased", this);
	private final LongArgumentSchema dailyIdArgument = new LongArgumentSchema("daily-id", "The daily id of the ticket", this);
	private final LongArgumentSchema attractionIdArgument = new LongArgumentSchema("attraction-id", "The id of the attraction visited", this);
}
