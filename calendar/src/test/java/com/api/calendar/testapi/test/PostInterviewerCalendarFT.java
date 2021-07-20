package com.api.calendar.testapi.test;

import com.api.calendar.data.dto.InterviewerScheduleDto;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.testapi.actions.UserActions;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@Tag("functional")
@ExtendWith(SpringExtension.class)
public class PostInterviewerCalendarFT extends TestSourceUsers{


    private static UserDTO userInesTest;
    private static UserDTO userIngridTest;
    private static InterviewerScheduleDto interviewerScheduleDtoCompleteValidTimeslots;
    private static InterviewerScheduleDto interviewerScheduleDtoInvalidStartAndEndDate;
    private static InterviewerScheduleDto interviewerScheduleDtoInversedDates;


    @BeforeAll
    public static void createUsersFT() throws Exception {
        UserActions.clearUsers(200);
        userInesTest = UserActions.createUser(userInesDTONoCalendar, 200);
        userIngridTest = UserActions.createUser(userIngridDTO, 200);
        interviewerScheduleDtoCompleteValidTimeslots = new InterviewerScheduleDto(userInesTest.getId(),"ines" , todayNoon, todayPlus4);
        interviewerScheduleDtoInvalidStartAndEndDate = new InterviewerScheduleDto(userIngridTest.getId(), "ingrid" , todayNoonHalf, todayPlus4Half);
        interviewerScheduleDtoInversedDates = new InterviewerScheduleDto(userIngridTest.getId(), "ingrid" , todayPlus4Half, todayNoonHalf);

    }

    @Test
    public void createInterviewerCalendarCompleteScheduleFT() throws IOException {
        UserActions.sendInterviewerSchedule(interviewerScheduleDtoCompleteValidTimeslots, 200 );
        UserDTO interviewerUpdated = UserActions.getInterviewer(userInesTest.getId(), 200);

        Assertions.assertEquals("ines", interviewerUpdated.getUserName());
        Assertions.assertEquals(4, interviewerUpdated.getInterviewCalendar().size());
    }

    @Test
    public void createInterviewerCalendarIncompleteScheduleFT() throws IOException {
        UserActions.sendInterviewerSchedule(interviewerScheduleDtoInvalidStartAndEndDate, 200 );
        UserDTO interviewerUpdated = UserActions.getInterviewer(userIngridTest.getId(), 200);

        Assertions.assertEquals("ingrid", interviewerUpdated.getUserName());
        Assertions.assertEquals(4, interviewerUpdated.getInterviewCalendar().size());
    }

    @Test
    public void createInterviewerCalendarWrongDatesFT() throws IOException {
        UserActions.sendInterviewerSchedule(interviewerScheduleDtoInversedDates, 200 );
    }
}
