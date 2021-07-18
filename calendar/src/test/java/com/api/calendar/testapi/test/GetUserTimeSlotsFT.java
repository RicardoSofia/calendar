package com.api.calendar.testapi.test;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.UserDTO;
import com.api.calendar.testapi.actions.UserActions;
import java.util.List;
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
public class GetUserTimeSlotsFT extends TestSourceUsers{

    private UserActions userActions = new UserActions();

    @BeforeAll
    public static void createUsersFT() throws Exception {
        UserActions.clearUsers(200);
        UserActions.createUser(userInesDTO, 200);
        UserActions.createUser(userIngridDTO, 200);
        UserActions.createUser(userCandidate, 200);
    }

    @Test
    public void getExistingCandidateFT() throws Exception {

        UserDTO candidate = userActions.getCandidate(3, 200);

        Assertions.assertEquals("candidate", candidate.getUserName());
    }

    @Test
    public void getCandidateBookedTimeslotFT() throws Exception {

        CalendarDTO candidateBookedTimeslot = userActions.getCandidateBookedTimeslot(3, 200);

        Assertions.assertNotNull(candidateBookedTimeslot);

    }

    @Test
    public void getExistingInterviewerFT() throws Exception {

        UserDTO interviewer = userActions.getInterviewer(1, 200);

        Assertions.assertEquals("ines", interviewer.getUserName());
    }

    @Test
    public void getInterviewerTimeslotsFT() throws Exception {

        List<CalendarDTO> userAvailableTimeslots = userActions.getInterviewerAvailableTimeslots(1, 200);

        Assertions.assertEquals(3, userAvailableTimeslots.size());

    }

    @Test
    public void getInterviewersCrossedAvaliableTimeslotsFT() throws Exception {
        List<CalendarDTO> userAvailableTimeslots = userActions.getUsersCrossedAvailableTimeslots(1, 2, 200);

        Assertions.assertEquals(1, userAvailableTimeslots.size());
    }

}
