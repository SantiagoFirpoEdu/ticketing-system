package com.firpy.model;

import com.firpy.repositories.Identifiable;

public record Ticket(TicketId id, IVisitor adultVisitor) implements Identifiable<TicketId>
{
    @Override
    public TicketId getId()
    {
        return id;
    }
}
