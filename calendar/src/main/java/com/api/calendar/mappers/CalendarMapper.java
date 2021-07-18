package com.api.calendar.mappers;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.entity.CalendarDb;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface CalendarMapper {

    CalendarDb mapCalendarDTOToCalendarDB(CalendarDTO calendarDTO);

    CalendarDTO mapCalendarDBToCalendarDTO(CalendarDb calendarDb);

    List<CalendarDb> mapListCalendarDTOToListCalendarDB(List<CalendarDTO> listCalendarDTO);

    List<CalendarDTO> mapListCalendarDBToListCalendarDTO(List<CalendarDb> listCalendarDB);
}
