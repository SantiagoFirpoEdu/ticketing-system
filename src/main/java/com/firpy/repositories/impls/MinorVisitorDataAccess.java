package com.firpy.repositories.impls;

import com.firpy.model.MinorVisitor;
import com.firpy.model.Visitor;
import com.firpy.repositories.CrudRepository;
import com.firpy.repositories.exceptions.CheckedIllegalArgumentException;

import java.time.LocalDate;
import java.util.Optional;

public class MinorVisitorDataAccess
{
    public MinorVisitorDataAccess(CrudRepository<Visitor, Long> visitorRepository, CrudRepository<MinorVisitor, Long> minorVisitorRepository)
    {
        this.visitorRepository = visitorRepository;
        this.minorVisitorRepository = minorVisitorRepository;
    }

    public MinorVisitor registerMinorVisitor(String name, LocalDate dateOfBirth, Long guardianId) throws CheckedIllegalArgumentException
    {
        Optional<Visitor> guardian = visitorRepository.findById(guardianId);

        if (guardian.isEmpty())
        {
            throw new CheckedIllegalArgumentException("Guardian with ID %d not found.".formatted(guardianId));
        }

        MinorVisitor visitor = new MinorVisitor(0, name, dateOfBirth, guardian.get());
        minorVisitorRepository.save(visitor);
        return visitor;
    }

    private final CrudRepository<Visitor, Long> visitorRepository;
    private final CrudRepository<MinorVisitor, Long> minorVisitorRepository;
}
