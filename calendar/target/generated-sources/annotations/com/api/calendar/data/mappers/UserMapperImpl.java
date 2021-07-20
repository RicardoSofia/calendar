package com.api.calendar.data.mappers;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.entity.CalendarTimeslotDb;
import com.api.calendar.data.entity.UserDb;
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
        userDb.setInterviewCalendar( calendarTimeslotDTOListToCalendarTimeslotDbList( userDTO.getInterviewCalendar() ) );

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
        userDTO.setInterviewCalendar( calendarTimeslotDbListToCalendarTimeslotDTOList( userDb.getInterviewCalendar() ) );

        return userDTO;
    }

    protected CalendarTimeslotDb calendarTimeslotDTOToCalendarTimeslotDb(CalendarTimeslotDTO calendarTimeslotDTO) {
        if ( calendarTimeslotDTO == null ) {
            return null;
        }

        CalendarTimeslotDb calendarTimeslotDb = new CalendarTimeslotDb();

        calendarTimeslotDb.setId( calendarTimeslotDTO.getId() );
        calendarTimeslotDb.setDateTime( calendarTimeslotDTO.getDateTime() );

        return calendarTimeslotDb;
    }

    protected List<CalendarTimeslotDb> calendarTimeslotDTOListToCalendarTimeslotDbList(List<CalendarTimeslotDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CalendarTimeslotDb> list1 = new ArrayList<CalendarTimeslotDb>( list.size() );
        for ( CalendarTimeslotDTO calendarTimeslotDTO : list ) {
            list1.add( calendarTimeslotDTOToCalendarTimeslotDb( calendarTimeslotDTO ) );
        }

        return list1;
    }

    protected CalendarTimeslotDTO calendarTimeslotDbToCalendarTimeslotDTO(CalendarTimeslotDb calendarTimeslotDb) {
        if ( calendarTimeslotDb == null ) {
            return null;
        }

        CalendarTimeslotDTO calendarTimeslotDTO = new CalendarTimeslotDTO();

        calendarTimeslotDTO.setId( calendarTimeslotDb.getId() );
        calendarTimeslotDTO.setDateTime( calendarTimeslotDb.getDateTime() );

        return calendarTimeslotDTO;
    }

    protected List<CalendarTimeslotDTO> calendarTimeslotDbListToCalendarTimeslotDTOList(List<CalendarTimeslotDb> list) {
        if ( list == null ) {
            return null;
        }

        List<CalendarTimeslotDTO> list1 = new ArrayList<CalendarTimeslotDTO>( list.size() );
        for ( CalendarTimeslotDb calendarTimeslotDb : list ) {
            list1.add( calendarTimeslotDbToCalendarTimeslotDTO( calendarTimeslotDb ) );
        }

        return list1;
    }
}
