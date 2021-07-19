package com.api.calendar.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ScheduleDateValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleDateConstraint {

    String message() default "Invalid Start/End Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
