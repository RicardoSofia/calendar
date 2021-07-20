package com.api.calendar.testapi.test;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.entity.CalendarTimeslotDb;
import com.api.calendar.data.entity.UserDb;
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

    public static CalendarTimeslotDTO calendarDTO1 = new CalendarTimeslotDTO(null, Timestamp.valueOf(todayNoon));
    public static CalendarTimeslotDTO calendarDTO2 = new CalendarTimeslotDTO(null, Timestamp.valueOf(todayPlus1));
    public static CalendarTimeslotDTO calendarDTO3 = new CalendarTimeslotDTO(null, Timestamp.valueOf(todayPlus2));
    public static List<CalendarTimeslotDTO> calendarDtoList = new ArrayList<>(Arrays.asList(calendarDTO1, calendarDTO2, calendarDTO3));
    public static List<CalendarTimeslotDTO> calendarDtoList2 = new ArrayList<>(Arrays.asList(calendarDTO1, calendarDTO2));

    public static UserDTO userInesDTO = new UserDTO(1,"ines", calendarDtoList);

    public static List<CalendarTimeslotDTO>  calendarDTOList = Collections.singletonList(calendarDTO1);
    public static UserDTO userCandidateDTO = new UserDTO(3, "candidate", calendarDTOList);
    public static UserDTO userCandidateDTONoCalendar = new UserDTO(3, "candidate", new ArrayList<>());
    public static UserDTO userInesDTONoCalendar = new UserDTO(1,"ines", new ArrayList<>());

    public static UserDTO userIngridDTO = new UserDTO(2, "ingrid", calendarDTOList);

    public static LocalDateTime todayPlus4 = todayNoon.plusHours(4);

    public static LocalDateTime todayNoonHalf = todayNoon.plusMinutes(30);
    public static LocalDateTime todayPlus4Half = todayNoonHalf.plusHours(4);

    public static UserDb userInesDb = new UserDb(1, "ines", new ArrayList<>());
    public static UserDb userIngridDb = new UserDb(2, "ingrid", new ArrayList<>());
    public static UserDb userCandidateDb = new UserDb(3, "candidate", new ArrayList<>());


    public static CalendarTimeslotDb calendarDb1 = new CalendarTimeslotDb(null, Timestamp.valueOf(todayNoon), userInesDb);
    public static CalendarTimeslotDb calendarDb2 = new CalendarTimeslotDb(null, Timestamp.valueOf(todayPlus1), userInesDb);
    public static CalendarTimeslotDb calendarDb3 = new CalendarTimeslotDb(null, Timestamp.valueOf(todayPlus2), userInesDb);
    public static List<CalendarTimeslotDb> calendarDbList = new ArrayList<>(Arrays.asList(calendarDb1, calendarDb2, calendarDb3));
    public static List<CalendarTimeslotDb> calendarDbList2 = new ArrayList<>(Arrays.asList(calendarDb1, calendarDb2));

}
