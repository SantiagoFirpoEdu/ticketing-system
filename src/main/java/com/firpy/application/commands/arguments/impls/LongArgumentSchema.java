package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;

public class LongArgumentSchema extends ArgumentSchema
{
    public LongArgumentSchema(String name, String description, Command command)
    {
        super(name, description, command);
    }

    @Override
    public Long parse(String[] args) throws CommandException
    {
        checkSize(args);
        String arg = args[getIndex()];
        try
        {
            return Long.parseLong(arg);
        }
        catch (NumberFormatException e)
        {
            throw new CommandException("Invalid long format: %s".formatted(arg));
        }
    }
}
