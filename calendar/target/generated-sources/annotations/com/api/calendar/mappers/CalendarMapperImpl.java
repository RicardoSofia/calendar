package com.api.calendar.mappers;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.entity.CalendarDb;
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
    public CalendarDb mapCalendarDTOToCalendarDB(CalendarDTO calendarDTO) {
        if ( calendarDTO == null ) {
            return null;
        }

        CalendarDb calendarDb = new CalendarDb();

        calendarDb.setId( calendarDTO.getId() );
        calendarDb.setDateTime( calendarDTO.getDateTime() );

        return calendarDb;
    }

    @Override
    public CalendarDTO mapCalendarDBToCalendarDTO(CalendarDb calendarDb) {
        if ( calendarDb == null ) {
            return null;
        }

        CalendarDTO calendarDTO = new CalendarDTO();

        calendarDTO.setId( calendarDb.getId() );
        calendarDTO.setDateTime( calendarDb.getDateTime() );

        return calendarDTO;
    }

    @Override
    public List<CalendarDb> mapListCalendarDTOToListCalendarDB(List<CalendarDTO> listCalendarDTO) {
        if ( listCalendarDTO == null ) {
            return null;
        }

        List<CalendarDb> list = new ArrayList<CalendarDb>( listCalendarDTO.size() );
        for ( CalendarDTO calendarDTO : listCalendarDTO ) {
            list.add( mapCalendarDTOToCalendarDB( calendarDTO ) );
        }

        return list;
    }

    @Override
    public List<CalendarDTO> mapListCalendarDBToListCalendarDTO(List<CalendarDb> listCalendarDB) {
        if ( listCalendarDB == null ) {
            return null;
        }

        List<CalendarDTO> list = new ArrayList<CalendarDTO>( listCalendarDB.size() );
        for ( CalendarDb calendarDb : listCalendarDB ) {
            list.add( mapCalendarDBToCalendarDTO( calendarDb ) );
        }

        return list;
    }
}
