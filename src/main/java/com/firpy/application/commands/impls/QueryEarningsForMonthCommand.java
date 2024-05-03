package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class QueryEarningsForMonthCommand extends Command
{
	public QueryEarningsForMonthCommand()
	{
		super("query-earnings-for-month", "Query earnings for a month.");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		//TODO implement earnings query for a month
	}
}
