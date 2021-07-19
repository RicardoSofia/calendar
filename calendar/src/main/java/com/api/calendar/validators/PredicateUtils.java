package com.api.calendar.validators;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class PredicateUtils {

    public static BiPredicate<LocalDateTime, LocalDateTime> localDateTimeLocalDateTimeBiPredicate = (startDateTime, endDate) -> startDateTime.isBefore(endDate);

    public static Predicate<Integer> localDateIsValidMinute = (minuteDate) -> minuteDate == 0;

    public static Function<LocalDateTime, LocalDateTime> formatStartDate = (startDate) -> startDate.withMinute(0).plusHours(1);

    public static Function<LocalDateTime, LocalDateTime> formatEndDate = (endDate) -> endDate.withMinute(0);
}
