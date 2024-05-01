package com.firpy.model;

import com.firpy.repositories.Identifiable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public record MinorVisitor(long id, String name, LocalDate dateOfBirth, Visitor guardian) implements Identifiable<Long>
{
	@Override
	public @NotNull Long getId()
	{
		return id;
	}
}
