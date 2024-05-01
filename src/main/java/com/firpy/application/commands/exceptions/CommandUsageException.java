package com.firpy.application.commands.exceptions;

import com.firpy.application.commands.Command;

public class CommandUsageException extends Exception
{
	public CommandUsageException(String message, Command command)
	{
		super(message + "\nUsage: %s".formatted(command.usage()));
	}
}
