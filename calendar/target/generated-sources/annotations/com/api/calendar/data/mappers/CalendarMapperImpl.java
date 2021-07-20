package com.api.calendar.data.mappers;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.entity.CalendarTimeslotDb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class CalendarMapperImpl implements CalendarMapper {

    @Override
    public CalendarTimeslotDb mapCalendarDTOToCalendarDB(CalendarTimeslotDTO calendarDTO) {
        if ( calendarDTO == null ) {
            return null;
        }

        CalendarTimeslotDb calendarTimeslotDb = new CalendarTimeslotDb();

        calendarTimeslotDb.setId( calendarDTO.getId() );
        calendarTimeslotDb.setDateTime( calendarDTO.getDateTime() );

        return calendarTimeslotDb;
    }

    @Override
    public CalendarTimeslotDTO mapCalendarDBToCalendarDTO(CalendarTimeslotDb calendarTimeslotDb) {
        if ( calendarTimeslotDb == null ) {
            return null;
        }

        CalendarTimeslotDTO calendarTimeslotDTO = new CalendarTimeslotDTO();

        calendarTimeslotDTO.setId( calendarTimeslotDb.getId() );
        calendarTimeslotDTO.setDateTime( calendarTimeslotDb.getDateTime() );

        return calendarTimeslotDTO;
    }

    @Override
    public List<CalendarTimeslotDb> mapListCalendarDTOToListCalendarDB(List<CalendarTimeslotDTO> listCalendarDTO) {
        if ( listCalendarDTO == null ) {
            return null;
        }

        List<CalendarTimeslotDb> list = new ArrayList<CalendarTimeslotDb>( listCalendarDTO.size() );
        for ( CalendarTimeslotDTO calendarTimeslotDTO : listCalendarDTO ) {
            list.add( mapCalendarDTOToCalendarDB( calendarTimeslotDTO ) );
        }

        return list;
    }

    @Override
    public List<CalendarTimeslotDTO> mapListCalendarDBToListCalendarDTO(List<CalendarTimeslotDb> listCalendarTimeslotDB) {
        if ( listCalendarTimeslotDB == null ) {
            return null;
        }

        List<CalendarTimeslotDTO> list = new ArrayList<CalendarTimeslotDTO>( listCalendarTimeslotDB.size() );
        for ( CalendarTimeslotDb calendarTimeslotDb : listCalendarTimeslotDB ) {
            list.add( mapCalendarDBToCalendarDTO( calendarTimeslotDb ) );
        }

        return list;
    }
}
