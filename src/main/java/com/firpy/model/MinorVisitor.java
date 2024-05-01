package com.firpy.model;

import com.firpy.repositories.Identifiable;

import java.time.LocalDate;

public record MinorVisitor(long id, String name, LocalDate dateOfBirth, Visitor guardian) implements Identifiable<Long>
{
	@Override
	public Long getId()
	{
		return id;
	}
}
