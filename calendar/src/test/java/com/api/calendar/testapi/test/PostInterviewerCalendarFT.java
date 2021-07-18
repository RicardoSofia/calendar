package com.api.calendar.testapi.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@Tag("functional")
@ExtendWith(SpringExtension.class)
public class PostInterviewerCalendarFT extends TestSourceUsers{


    @Test
    public void createInterviewerCalendarCompleteScheduleFT() {
//        interviewerDtoCompleteValidTimeslots;
    }

    @Test
    public void createInterviewerCalendarIncompleteScheduleFT() {
//        interviewerDtoInvalidStartAndEndDate;
    }
}
