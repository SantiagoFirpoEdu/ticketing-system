package com.firpy.application.commands.arguments.impls;

import com.firpy.application.commands.Command;
import com.firpy.application.commands.arguments.ArgumentSchema;
import com.firpy.application.commands.exceptions.CommandException;
import com.firpy.application.commands.exceptions.CommandUsageException;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull LocalDate parse(String @NotNull [] args) throws CommandUsageException
    {
        checkSize(args);
        String arg = args[getIndex()];
        try
        {
            return LocalDate.parse(arg, DateTimeFormatter.ofPattern(DATE_PATTERN));
        }
        catch (DateTimeParseException e)
        {
            throw new CommandUsageException("Invalid date format: %s".formatted(arg), getCommand());
        }
    }

    private static final String DATE_PATTERN = "dd-MM-yyyy";
}
