package com.api.calendar.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class InterviewerDto {

    private String userName;

    private LocalDateTime startCalendar;

    private LocalDateTime endCalendar;

}
