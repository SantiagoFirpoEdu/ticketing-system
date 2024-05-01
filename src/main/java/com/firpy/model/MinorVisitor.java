package com.firpy.model;

import com.firpy.repositories.Identifiable;

import java.util.Date;

public record MinorVisitor(long id, String name, Date dateOfBirth, Visitor guardian) implements Identifiable<Long>
{
	@Override
	public Long getId()
	{
		return id;
	}
}
