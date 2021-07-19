package com.api.calendar.service;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.UserDTO;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface InterviewCalendarInterface extends UserInterface{

    List<CalendarDTO> getUserCalendar(Integer userId);

    UserDTO bookUserCalendarSlots(Integer userId, List<CalendarDTO> calendarDTOList)
        throws NotFoundException;

    List<CalendarDTO> getUsersCrossedCalendar(Integer interviewerId1, Integer interviewerId2)
        throws NotFoundException;

}