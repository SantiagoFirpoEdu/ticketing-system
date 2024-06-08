package com.firpy.application;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class UserDataAccess
{
	public Optional<String> getCurrentUserName()
	{
		return currentUserName;
	}

	public void setCurrentUserName(@NotNull String currentUserName)
	{
		this.currentUserName = Optional.of(currentUserName);
	}

	private @NotNull Optional<String> currentUserName = Optional.empty();
}
