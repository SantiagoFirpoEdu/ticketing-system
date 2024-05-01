package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateArgumentSchema extends ArgumentSchema
{
    public LocalDateArgumentSchema(String name, String description, Command command)
    {
        super(name, "%s. Format is %s".formatted(description, DATE_PATTERN), command);
    }

    @Override
    public LocalDate parse(String[] args) throws CommandException
    {
        checkSize(args);
        String arg = args[getIndex()];
        try
        {
            return LocalDate.parse(arg, DateTimeFormatter.ofPattern(DATE_PATTERN));
        }
        catch (DateTimeParseException e)
        {
            throw new CommandException("Invalid date format: %s".formatted(arg));
        }
    }

    private static final String DATE_PATTERN = "dd-MM-yyyy";
}
