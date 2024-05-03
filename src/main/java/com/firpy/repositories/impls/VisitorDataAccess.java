package com.firpy.repositories.impls;

import com.firpy.model.IVisitor;
import com.firpy.model.MinorVisitor;
import com.firpy.model.AdultVisitor;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.exceptions.CheckedIllegalArgumentException;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitorDataAccess
{
    public VisitorDataAccess(CrudRepository<AdultVisitor, Long> visitorRepository, CrudRepository<MinorVisitor, Long> minorVisitorRepository)
    {
        this.visitorRepository = visitorRepository;
        this.minorVisitorRepository = minorVisitorRepository;
    }

    public @NotNull MinorVisitor registerMinorVisitor(String name, LocalDate dateOfBirth, Long guardianId) throws CheckedIllegalArgumentException
    {
        Optional<AdultVisitor> guardian = visitorRepository.findById(guardianId);

        if (guardian.isEmpty())
        {
            throw new CheckedIllegalArgumentException("Guardian with ID %d not found.".formatted(guardianId));
        }

        MinorVisitor visitor = new MinorVisitor(nextId++, name, dateOfBirth, guardian.get());
        minorVisitorRepository.save(visitor);

        return visitor;
    }

    public AdultVisitor registerAdultVisitor(String name, LocalDate dateOfBirth, String phoneNumber)
    {
        AdultVisitor adultVisitor = new AdultVisitor(nextId++, name, dateOfBirth, phoneNumber);
        visitorRepository.save(adultVisitor);

        return adultVisitor;
    }

    public List<IVisitor> findAllVisitors()
    {
        ArrayList<IVisitor> allVisitors = new ArrayList<>(visitorRepository.findAll());
        allVisitors.addAll(minorVisitorRepository.findAll());

        return allVisitors;
    }

    private final CrudRepository<AdultVisitor, Long> visitorRepository;
    private final CrudRepository<MinorVisitor, Long> minorVisitorRepository;

    private int nextId = 0;
}
