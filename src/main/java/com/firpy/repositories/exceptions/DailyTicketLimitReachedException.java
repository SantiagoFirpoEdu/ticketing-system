package com.firpy.repositories.exceptions;

import java.time.LocalDate;

public class DailyTicketLimitReachedException extends Exception
{
	public DailyTicketLimitReachedException(LocalDate date)
	{
		super("Daily ticket limit reached for %s".formatted(date));
	}
	public DailyTicketLimitReachedException(String message) {
		super(message);
	}
}
