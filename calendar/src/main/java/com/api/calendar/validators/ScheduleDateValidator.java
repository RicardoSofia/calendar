package com.api.calendar.validators;

import static com.api.calendar.validators.PredicateUtils.localDateTimeLocalDateTimeBiPredicate;

import com.api.calendar.data.dto.InterviewerScheduleDto;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScheduleDateValidator implements ConstraintValidator<Constraint, InterviewerScheduleDto> {


    @Override
    public void initialize(Constraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InterviewerScheduleDto interviewerScheduleDto,
        ConstraintValidatorContext constraintValidatorContext) {
        return localDateTimeLocalDateTimeBiPredicate.test(interviewerScheduleDto.getStartCalendar(), interviewerScheduleDto
            .getEndCalendar());
    }
}
