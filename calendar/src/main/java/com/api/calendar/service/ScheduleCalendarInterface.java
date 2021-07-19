package com.api.calendar.service;

import com.api.calendar.dto.InterviewerDto;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface ScheduleCalendarInterface extends UserInterface{

    void ScheduleInterviewerTimeslots(InterviewerDto interviewerDto) throws NotFoundException;
}
