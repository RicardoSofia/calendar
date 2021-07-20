package com.api.calendar.data.dto;

import com.api.calendar.validators.ScheduleDateConstraint;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@ScheduleDateConstraint
public class InterviewerScheduleDto {

    @NotNull
    private Integer id;

    private String userName;

    @NotNull
    private LocalDateTime startCalendar;
    @NotNull
    private LocalDateTime endCalendar;

}
