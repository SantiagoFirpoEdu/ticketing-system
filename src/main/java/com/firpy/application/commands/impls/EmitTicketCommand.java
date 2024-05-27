package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.impls.LocalDateArgumentSchema;
import com.firpy.application.commands.arguments.impls.LongArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class EmitTicketCommand extends Command
{
	public EmitTicketCommand()
	{
		super("emit-ticket", "Emit a ticket for a visitor.");
		addArgumentSchemas(visitorIdArgument, purchaseDateArgument);
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		long visitorId = visitorIdArgument.parse(args);
		LocalDate purchaseDate = purchaseDateArgument.parse(args);
	}

	private final LongArgumentSchema visitorIdArgument = new LongArgumentSchema("visitor-id", "Visitor ID to emit a ticket for.", this);
	private final LocalDateArgumentSchema purchaseDateArgument = new LocalDateArgumentSchema("purchase-date", "Purchase date of the ticket.", this);
}
