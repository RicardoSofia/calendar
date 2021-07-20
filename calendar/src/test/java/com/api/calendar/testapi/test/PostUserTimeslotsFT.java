package com.api.calendar.testapi.test;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.testapi.actions.UserActions;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("functional")
@ExtendWith(SpringExtension.class)
public class PostUserTimeslotsFT extends TestSourceUsers{

    private static UserDTO userInesTest;
    private static UserDTO userIngridTest;
    private static UserDTO userCandidateTest;

    @BeforeAll
    public static void createUsersFT() throws Exception {
        UserActions.clearUsers(200);
        userInesTest = UserActions.createUser(userInesDTO, 200);
        userIngridTest = UserActions.createUser(userIngridDTO, 200);
        userCandidateTest = UserActions.createUser(userCandidateDTONoCalendar, 200);
    }

    @Test
    void testPostUpdateInterviewerFT() throws IOException {

        UserActions.updateInterviewerWithTimeslots(userInesTest.getId(), userInesTest, 200 );
        UserDTO interviewerUpdated = UserActions.getInterviewer(userInesTest.getId(), 200);

        Assertions.assertEquals("ines", interviewerUpdated.getUserName());
        Assertions.assertEquals(3, interviewerUpdated.getInterviewCalendar().size());
    }

    @Test
    void testPutUpdateInterviewerFT() throws IOException {
        UserDTO userDTO = UserActions
            .updateInterviewerWithTimeslotsById(userIngridTest.getId(), calendarDtoList, 200);

        Assertions.assertEquals(4, userDTO.getInterviewCalendar().size());

    }

    @Test
    void sendCandidateTimeslotValidTimeslotFT() throws IOException {
        List<CalendarTimeslotDTO> usersCrossedAvailableTimeslots = UserActions
            .getUsersCrossedAvailableTimeslots(userInesTest.getId(), userIngridTest.getId(), 200);
        if(usersCrossedAvailableTimeslots.size() > 0) {
            userCandidateTest.getInterviewCalendar().add(usersCrossedAvailableTimeslots.get(0));
            UserActions.sendCandidateTimeslot(userCandidateTest.getId(), userCandidateTest,200);
            UserDTO candidateDto = UserActions.getCandidate(userCandidateTest.getId(), 200);

            List<CalendarTimeslotDTO> interviewCalendar = candidateDto.getInterviewCalendar();
            Assertions.assertNotNull(interviewCalendar);
            Assertions.assertEquals(1, interviewCalendar.size());
            Assertions.assertEquals(userCandidateDTO.getInterviewCalendar().get(0).getDateTime(),
                usersCrossedAvailableTimeslots.get(0).getDateTime());
        }
    }
}
