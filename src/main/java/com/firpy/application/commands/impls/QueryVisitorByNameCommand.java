package com.firpy.application.commands.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.Shell;
import org.jetbrains.annotations.NotNull;

public class QueryVisitorByNameCommand extends Command
{
	public QueryVisitorByNameCommand()
	{
		super("query-visitor-by-name-part", "Query a visitor by a name substring.");
	}

	@Override
	public void run(@NotNull String[] args, @NotNull Shell shell) throws CommandException, CommandUsageException
	{
		//TODO implement visitor query by a name substring
	}
}
