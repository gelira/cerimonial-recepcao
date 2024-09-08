package com.cerimonial.backend.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringDateFormatValidator implements ConstraintValidator<StringDateFormat, String> {
    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
