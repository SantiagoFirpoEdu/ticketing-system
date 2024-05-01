package com.firpy.model;

import com.firpy.repositories.Identifiable;
import org.jetbrains.annotations.NotNull;

public record Attraction(long id, String name) implements Identifiable<Long>
{
    @Override
    public @NotNull Long getId()
    {
        return id;
    }
}
