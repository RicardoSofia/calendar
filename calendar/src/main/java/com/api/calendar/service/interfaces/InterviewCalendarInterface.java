package com.api.calendar.service.interfaces;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface InterviewCalendarInterface extends UserInterface {

    List<CalendarTimeslotDTO> getUserCalendar(Integer userId);

    UserDTO bookUserCalendarSlots(Integer userId, List<CalendarTimeslotDTO> calendarDTOList)
        throws NotFoundException;

    List<CalendarTimeslotDTO> getUsersCrossedCalendar(Integer interviewerId1, Integer interviewerId2)
        throws NotFoundException;

}
