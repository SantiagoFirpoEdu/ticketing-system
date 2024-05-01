package com.firpy.application.commands.arguments;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import com.firpy.application.shell.PrettyPrintable;
import com.firpy.application.shell.ShellColors;
import org.jetbrains.annotations.NotNull;

public abstract class ArgumentSchema implements PrettyPrintable
{
	protected ArgumentSchema(String name, String description, Command command)
	{
		this.name = name;
		this.description = description;
        this.command = command;
    }

	protected void checkSize(String @NotNull [] args) throws CommandUsageException
	{
		if (getIndex() >= args.length)
		{
			throw new CommandUsageException("Missing argument: %s".formatted(getName()), command);
		}
	}

	protected int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	@Override
	public String prettyPrint()
	{
		return "%s%s:%s %s".formatted(ShellColors.GREEN, name, ShellColors.RESET, description);
	}

	public String getName()
	{
		return name;
	}

	public abstract Object parse(String[] args) throws CommandException, CommandUsageException;

	protected final Command getCommand()
	{
		return command;
	}

	private final String name;
	private final String description;
	private final Command command;
	private int index;
}
