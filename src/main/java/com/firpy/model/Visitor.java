package com.firpy.model;

import com.firpy.repositories.Identifiable;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public record Visitor(long id, String name, Date dateOfBirth, String phoneNumber) implements Identifiable<Long>
{
	@Override
	public @NotNull Long getId()
	{
		return id;
	}
}