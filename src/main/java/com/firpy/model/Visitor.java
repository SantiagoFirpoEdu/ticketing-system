package com.firpy.model;

import com.firpy.repositories.Identifiable;

import java.util.Date;

public record Visitor(long id, String name, Date dateOfBirth, String phoneNumber) implements Identifiable<Long>
{
	@Override
	public Long getId()
	{
		return id;
	}
}