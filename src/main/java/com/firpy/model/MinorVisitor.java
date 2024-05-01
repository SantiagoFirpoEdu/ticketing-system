package com.firpy.model;

import java.util.Date;

public record MinorVisitor(long id, String name, Date dateOfBirth, Visitor guardian)
{
}
