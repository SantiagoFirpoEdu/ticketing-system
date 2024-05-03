package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

public class ListVisitors extends Command
{
	public ListVisitors(VisitorDataAccess visitorDataAccess)
	{
		super("list-visitors", "Lists all visitors.");
		this.visitorDataAccess = visitorDataAccess;
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		//TODO: print all visitors
	}

	private final VisitorDataAccess visitorDataAccess;
}
