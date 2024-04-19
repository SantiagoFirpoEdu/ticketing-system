package com.firpy.model;

import java.util.Date;

public record Ticket(Visitor visitor, Date purchaseDate, long dailyId)
{
}
