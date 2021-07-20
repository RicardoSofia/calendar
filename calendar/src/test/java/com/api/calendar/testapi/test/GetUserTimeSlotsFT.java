package com.api.calendar.testapi.test;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
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

    private static UserDTO userInesTest;
    private static UserDTO userIngridTest;
    private static UserDTO userCandidateTest;

    @BeforeAll
    public static void createUsersFT() throws Exception {
        UserActions.clearUsers(200);
        userInesTest = UserActions.createUser(userInesDTO, 200);
        userIngridTest = UserActions.createUser(userIngridDTO, 200);
        userCandidateTest = UserActions.createUser(userCandidateDTO, 200);
    }

    @Test
    public void getExistingCandidateFT() throws Exception {

        UserDTO candidate = userActions.getCandidate(userCandidateTest.getId(), 200);

        Assertions.assertEquals("candidate", candidate.getUserName());
    }

    @Test
    public void getCandidateBookedTimeslotFT() throws Exception {

        CalendarTimeslotDTO candidateBookedTimeslot = userActions.getCandidateBookedTimeslot(userCandidateTest.getId(), 200);

        Assertions.assertNotNull(candidateBookedTimeslot);

    }

    @Test
    public void getExistingInterviewerFT() throws Exception {

        UserDTO interviewer = userActions.getInterviewer(userInesTest.getId(), 200);

        Assertions.assertEquals("ines", interviewer.getUserName());
    }

    @Test
    public void getInterviewerTimeslotsFT() throws Exception {

        List<CalendarTimeslotDTO> userAvailableTimeslots = userActions.getInterviewerAvailableTimeslots(userInesTest.getId(), 200);

        Assertions.assertEquals(3, userAvailableTimeslots.size());

    }

    @Test
    public void getInterviewersCrossedAvaliableTimeslotsFT() throws Exception {
        List<CalendarTimeslotDTO> userAvailableTimeslots = userActions.getUsersCrossedAvailableTimeslots(userInesTest.getId(), userIngridTest.getId(), 200);

        Assertions.assertEquals(1, userAvailableTimeslots.size());
    }

}
