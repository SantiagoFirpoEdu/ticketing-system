package com.firpy.model;

import java.time.LocalDate;
public record TicketId(LocalDate purchaseDate, long dailyId)
{}
