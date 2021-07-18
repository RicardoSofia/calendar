package com.api.calendar.testapi.test;

import com.api.calendar.dto.UserDTO;
import com.api.calendar.entity.UserDb;
import com.api.calendar.testapi.actions.UserActions;
import java.io.IOException;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("functional")
@ExtendWith(SpringExtension.class)
public class PostUserTimeslotsFT extends TestSourceUsers{

    private static UserDTO userInes;
    private static UserDTO userIngrid;
    private static UserDTO userCan;

    @BeforeAll
    public static void createUsersFT() throws Exception {
        UserActions.clearUsers(200);
        userInes = UserActions.createUser(userInesDTO, 200);
        userIngrid = UserActions.createUser(userIngridDTO, 200);
        userCan = UserActions.createUser(userCandidate, 200);
    }

    @Test
    public void testUpdateInterviewerFT() throws IOException {

        UserActions.updateInterviewerWithTimeslots(userInes.getId(), userInes, 200 );
        UserDTO interviewerUpdated = UserActions.getInterviewer(userInes.getId(), 200);

        Assertions.assertEquals("ines", interviewerUpdated.getUserName());
        Assertions.assertEquals(3, interviewerUpdated.getInterviewCalendar().size());
    }

    @Test
    public void testPostInterviewerFT() throws IOException {
        UserActions.updateInterviewerWithTimeslotsById(userIngrid.getId(), calendarDtoList, 200);
        UserDTO interviewer = UserActions.getInterviewer(userIngrid.getId(), 200);

        Assertions.assertEquals(1, interviewer.getInterviewCalendar().size());

    }

    @Test
    public void sendCandidateTimeslotFT() throws IOException {
        UserActions.sendCandidateTimeslot(3,userCandidate,200);
    }
}
