package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class EmitTicketCommand extends Command
{
	public EmitTicketCommand()
	{
		super("emit-ticket", "Emit a ticket for a visitor.");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		//TODO: implement ticket emission
		//I don't know how make it
	}
}
