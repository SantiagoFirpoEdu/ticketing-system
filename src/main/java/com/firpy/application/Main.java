package com.firpy.application;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.firpy.application.commands.impls.*;
import com.firpy.application.shell.Shell;
import com.firpy.model.*;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.CrudValueRepository;
import com.firpy.repositories.impls.VisitorDataAccess;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        CrudRepository<MinorVisitor, Long> minorVisitorRepository = new CrudRepository<>();
        CrudRepository<AdultVisitor, Long> visitorRepository = new CrudRepository<>();
        CrudRepository<Attraction, Long> attractionRepository = new CrudRepository<>();
        CrudRepository<Ticket, TicketId> ticketRepository = new CrudRepository<>();
        CrudValueRepository<Visit> visitRepository = new CrudValueRepository<>();

        List<Visit> filtered = visitRepository.findManyByPredicate(visit -> visit.date().toLocalDate().isEqual(date));

        HashMap<Long, Integer> attractionVisitCount = new HashMap<>();

        for (Visit visit: filtered){
            attractionVisitCount.merge(visit.AttractionId(), 1, Integer::sum);
        }

        Long[] array = attractionVisitCount.keySet().toArray(Long[]::new);
        Arrays.sort(array, Comparator.comparing(attractionVisitCount::get).reversed());

        
        for (Long attractionId:array){
            System.out.println("Atração ID: " + attractionId + " - Visitantes: " + attractionVisitCount.get(attractionId));
        }

        VisitorDataAccess visitorDataAccess = new VisitorDataAccess(visitorRepository, minorVisitorRepository);
        Shell shell = new Shell
        (
            new RegisterVisitorCommand(visitorDataAccess),
            new RegisterMinorVisitorCommand(visitorDataAccess),
            new ListVisitors(visitorDataAccess),
            new ExitCommand(),
            new HelpCommand()
        );
        shell.help();
        shell.waitForInput();
    }
}