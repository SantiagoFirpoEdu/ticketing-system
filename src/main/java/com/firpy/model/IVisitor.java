package com.firpy.model;

import com.firpy.repositories.Identifiable;
import org.jetbrains.annotations.NotNull;

public interface IVisitor extends Identifiable<Long>
{
	@NotNull String getName();
}
