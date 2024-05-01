package com.firpy.application.commands.exceptions;

import com.firpy.application.commands.Command;
import org.jetbrains.annotations.NotNull;

public class CommandUsageException extends Exception
{
	public CommandUsageException(String message, @NotNull Command command)
	{
		super(message + "%nUsage: %s".formatted(command.usage()));
	}
}
