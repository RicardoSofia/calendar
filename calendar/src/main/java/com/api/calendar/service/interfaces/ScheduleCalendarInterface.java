package com.api.calendar.service.interfaces;

import com.api.calendar.data.dto.InterviewerScheduleDto;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface ScheduleCalendarInterface extends UserInterface {

    void ScheduleInterviewerTimeslots(InterviewerScheduleDto interviewerScheduleDto) throws NotFoundException;
}
