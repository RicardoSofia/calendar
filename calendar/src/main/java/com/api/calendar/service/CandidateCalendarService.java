package com.api.calendar.service;

import com.api.calendar.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CandidateCalendarService implements InterviewCalendarService {

    private final CandidateRepository candidateRepository;

    @Override
    public void getScheduledCalendar() {

    }
}
