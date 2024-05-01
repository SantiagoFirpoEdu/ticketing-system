package com.firpy.model;

import com.firpy.repositories.Identifiable;

public record Attraction(long id, String name) implements Identifiable<Long>
{
    @Override
    public Long getId()
    {
        return id;
    }
}
