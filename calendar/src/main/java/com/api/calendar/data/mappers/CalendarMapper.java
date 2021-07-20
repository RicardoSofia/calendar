package com.api.calendar.data.mappers;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.entity.CalendarTimeslotDb;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarMapper {

    CalendarTimeslotDb mapCalendarDTOToCalendarDB(CalendarTimeslotDTO calendarDTO);

    CalendarTimeslotDTO mapCalendarDBToCalendarDTO(CalendarTimeslotDb calendarTimeslotDb);

    List<CalendarTimeslotDb> mapListCalendarDTOToListCalendarDB(List<CalendarTimeslotDTO> listCalendarDTO);

    List<CalendarTimeslotDTO> mapListCalendarDBToListCalendarDTO(List<CalendarTimeslotDb> listCalendarTimeslotDB);
}
