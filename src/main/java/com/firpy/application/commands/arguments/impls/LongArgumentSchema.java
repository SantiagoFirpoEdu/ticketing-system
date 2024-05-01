package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import org.jetbrains.annotations.NotNull;

public class LongArgumentSchema extends ArgumentSchema
{
    public LongArgumentSchema(String name, String description, Command command)
    {
        super(name, description, command);
    }

    @Override
    public @NotNull Long parse(String @NotNull [] args) throws CommandException, CommandUsageException
    {
        checkSize(args);
        String arg = args[getIndex()];
        try
        {
            return Long.parseLong(arg);
        }
        catch (NumberFormatException e)
        {
            throw new CommandUsageException("Invalid long format: %s".formatted(arg), getCommand());
        }
    }
}
