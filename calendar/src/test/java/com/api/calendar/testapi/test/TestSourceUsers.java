package com.api.calendar.testapi.test;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.InterviewerDto;
import com.api.calendar.dto.UserDTO;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class TestSourceUsers {

    public static LocalTime noon = LocalTime.NOON;
    public static LocalDate today = LocalDate.now(ZoneId.of("Europe/London"));
    public static LocalDateTime todayNoon = LocalDateTime.of(today, noon);
    public static LocalDateTime todayPlus1 = todayNoon.plusHours(1);
    public static LocalDateTime todayPlus2 = todayNoon.plusHours(2);

    public static CalendarDTO calendarDTO1 = new CalendarDTO(null, Timestamp.valueOf(todayNoon));
    public static CalendarDTO calendarDTO2 = new CalendarDTO(null, Timestamp.valueOf(todayPlus1));
    public static CalendarDTO calendarDTO3 = new CalendarDTO(null, Timestamp.valueOf(todayPlus2));
    public static List<CalendarDTO> calendarDtoList = Arrays.asList(calendarDTO1, calendarDTO2, calendarDTO3);
    public static UserDTO userInesDTO = new UserDTO(1,"ines", calendarDtoList);

    public static List<CalendarDTO>  calendarDTOList = Collections.singletonList(calendarDTO1);
    public static UserDTO userCandidateDTO = new UserDTO(3, "candidate", calendarDTOList);
    public static UserDTO userCandidateDTONoCalendar = new UserDTO(3, "candidate", new ArrayList<>());
    public static UserDTO userInesDTONoCalendar = new UserDTO(1,"ines", new ArrayList<>());



    public static UserDTO userIngridDTO = new UserDTO(2, "ingrid", calendarDTOList);

    public static LocalDateTime todayPlus4 = todayNoon.plusHours(4);

    public static LocalDateTime todayNoonHalf = todayNoon.plusMinutes(30);
    public static LocalDateTime todayPlus4Half = todayNoonHalf.plusHours(4);

}
