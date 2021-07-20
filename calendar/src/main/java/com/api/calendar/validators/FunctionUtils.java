package com.api.calendar.validators;

import java.time.LocalDateTime;
import java.util.function.Function;

public abstract class FunctionUtils {

    public static Function<LocalDateTime, LocalDateTime> formatStartDate = (startDate) -> startDate.withMinute(0).plusHours(1);

    public static Function<LocalDateTime, LocalDateTime> formatEndDate = (endDate) -> endDate.withMinute(0);

}
