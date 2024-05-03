package com.firpy.model;

import com.firpy.repositories.Identifiable;

import java.time.LocalDateTime;

public record Visit(long visitorId, TicketId ticketId, LocalDateTime date) implements Identifiable<Visit>
{
	@Override
	public Visit getId()
	{
		return this;
	}
}
