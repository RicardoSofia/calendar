package com.api.calendar.testapi.connector;

import static com.api.calendar.testapi.util.UserEndpointEnum.CANDIDATE_TIMESLOTS;
import static com.api.calendar.testapi.util.UserEndpointEnum.INTERVIEWER_TIMESLOTS;
import static com.api.calendar.testapi.util.UserEndpointEnum.USER_CLEAR;
import static com.api.calendar.testapi.util.UserEndpointEnum.USER_CREATION;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.UserDTO;
import com.api.calendar.testapi.functionaltest.ApacheHttpConnector;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;

public class PostPutUsersConnector {

    public static HttpResponse clearUsers() throws IOException {
        String url = String.format(USER_CLEAR.getEndpoint());
        return new ApacheHttpConnector().httpDelete(url);
    }

    public static HttpResponse createUser(UserDTO userDto) throws IOException {
        String url = String.format(USER_CREATION.getEndpoint());
        return new ApacheHttpConnector().httpPut(url, userDto);
    }

    public static HttpResponse postInterviewerWithTimeslots(Integer userId, UserDTO interviewerDto) throws IOException {

        String url = String.format(INTERVIEWER_TIMESLOTS.getEndpoint(), userId);
        return new ApacheHttpConnector().httpPost(url, interviewerDto);
    }

    public static HttpResponse putInterviewerTimeslots(Integer userId, List<CalendarDTO> calendarDTOList) throws IOException {
        String url = String.format(INTERVIEWER_TIMESLOTS.getEndpoint(), userId);
        return new ApacheHttpConnector().httpPut(url, calendarDTOList);
    }

    public static HttpResponse postCandidateTimeslot(Integer userId, UserDTO userDto) throws IOException {
        String url = String.format(CANDIDATE_TIMESLOTS.getEndpoint(), userId);
        return new ApacheHttpConnector().httpPost(url, userDto);
    }


}
