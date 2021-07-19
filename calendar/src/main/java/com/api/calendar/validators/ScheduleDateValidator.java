package com.api.calendar.validators;

import static com.api.calendar.validators.PredicateUtils.localDateTimeLocalDateTimeBiPredicate;

import com.api.calendar.dto.InterviewerDto;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScheduleDateValidator implements ConstraintValidator<Constraint, InterviewerDto> {


    @Override
    public void initialize(Constraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InterviewerDto interviewerDto,
        ConstraintValidatorContext constraintValidatorContext) {
        return localDateTimeLocalDateTimeBiPredicate.test(interviewerDto.getStartCalendar(), interviewerDto.getEndCalendar());
    }
}
