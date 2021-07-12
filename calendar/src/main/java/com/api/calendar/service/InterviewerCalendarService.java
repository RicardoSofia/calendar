package com.api.calendar.service;

import com.api.calendar.repository.InterviewerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterviewerCalendarService implements InterviewCalendarService{

    private final InterviewerRepository interviewerRepository;

    @Override
    public void getScheduledCalendar() {

    }
}
