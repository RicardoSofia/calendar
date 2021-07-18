package com.api.calendar.mappers;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.UserDTO;
import com.api.calendar.entity.CalendarDb;
import com.api.calendar.entity.UserDb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDb mapUserDTOToUserDB(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserDb userDb = new UserDb();

        userDb.setId( userDTO.getId() );
        userDb.setUserName( userDTO.getUserName() );
        userDb.setInterviewCalendar( calendarDTOListToCalendarDbList( userDTO.getInterviewCalendar() ) );

        setUserInRelation( userDb );

        return userDb;
    }

    @Override
    public UserDTO mapUserDBToUserDTO(UserDb userDb) {
        if ( userDb == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userDb.getId() );
        userDTO.setUserName( userDb.getUserName() );
        userDTO.setInterviewCalendar( calendarDbListToCalendarDTOList( userDb.getInterviewCalendar() ) );

        return userDTO;
    }

    protected CalendarDb calendarDTOToCalendarDb(CalendarDTO calendarDTO) {
        if ( calendarDTO == null ) {
            return null;
        }

        CalendarDb calendarDb = new CalendarDb();

        calendarDb.setId( calendarDTO.getId() );
        calendarDb.setDateTime( calendarDTO.getDateTime() );

        return calendarDb;
    }

    protected List<CalendarDb> calendarDTOListToCalendarDbList(List<CalendarDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CalendarDb> list1 = new ArrayList<CalendarDb>( list.size() );
        for ( CalendarDTO calendarDTO : list ) {
            list1.add( calendarDTOToCalendarDb( calendarDTO ) );
        }

        return list1;
    }

    protected CalendarDTO calendarDbToCalendarDTO(CalendarDb calendarDb) {
        if ( calendarDb == null ) {
            return null;
        }

        CalendarDTO calendarDTO = new CalendarDTO();

        calendarDTO.setId( calendarDb.getId() );
        calendarDTO.setDateTime( calendarDb.getDateTime() );

        return calendarDTO;
    }

    protected List<CalendarDTO> calendarDbListToCalendarDTOList(List<CalendarDb> list) {
        if ( list == null ) {
            return null;
        }

        List<CalendarDTO> list1 = new ArrayList<CalendarDTO>( list.size() );
        for ( CalendarDb calendarDb : list ) {
            list1.add( calendarDbToCalendarDTO( calendarDb ) );
        }

        return list1;
    }
}
