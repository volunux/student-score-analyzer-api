package com.tplnt.studentscore.entity.exception;

import com.tplnt.studentscore.controller.exception.TechiePlanetException;

public class SubjectDuplicateEntityException extends TechiePlanetException {

    private static final long serialVersionUID = 1L;

    public static final String entityName = "Subject";


    @Override
    public String getMessage() {
        return String.format("%s entry already exists in record", entityName);
    }
}
