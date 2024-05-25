package com.firpy.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public record AdultVisitor(long id, String name, LocalDate dateOfBirth, String phoneNumber) implements Visitor
{
	@Override
	public @NotNull Long getId()
	{
		return id;
	}

	@Override
	public @NotNull String getName()
	{
		return name;
	}
}