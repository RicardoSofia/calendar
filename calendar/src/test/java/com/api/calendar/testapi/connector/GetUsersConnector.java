package com.api.calendar.testapi.connector;

import static com.api.calendar.testapi.util.UserEndpointEnum.CANDIDATE_ENDPOINT;
import static com.api.calendar.testapi.util.UserEndpointEnum.CANDIDATE_TIMESLOTS;
import static com.api.calendar.testapi.util.UserEndpointEnum.INTERVIEWER_CROSSED_TIMESLOTS;
import static com.api.calendar.testapi.util.UserEndpointEnum.INTERVIEWER_ENDPOINT;
import static com.api.calendar.testapi.util.UserEndpointEnum.INTERVIEWER_TIMESLOTS;

import com.api.calendar.testapi.functionaltest.ApacheHttpConnector;
import java.io.IOException;
import org.apache.http.HttpResponse;

public class GetUsersConnector {

    public static HttpResponse getCandidate(Integer userId) throws IOException {

        String url = String.format(CANDIDATE_ENDPOINT.getEndpoint(), userId);
        return new ApacheHttpConnector().httpGet(url);
    }

    public static HttpResponse getCandidateBookedTimeslot(Integer userId) throws IOException {
        String url = String.format(CANDIDATE_TIMESLOTS.getEndpoint(), userId);
        return new ApacheHttpConnector().httpGet(url);
    }

    public static HttpResponse getInterviewer(Integer userId) throws IOException {

        String url = String.format(INTERVIEWER_ENDPOINT.getEndpoint(), userId);
        return new ApacheHttpConnector().httpGet(url);
    }

    public static HttpResponse getInterviewerTimeslots(Integer userId) throws IOException {

        String url = String.format(INTERVIEWER_TIMESLOTS.getEndpoint(), userId);
        return new ApacheHttpConnector().httpGet(url);
    }

    public static HttpResponse getInterviewersCrossedTimeslots(Integer userId, Integer userId2)
        throws IOException {
        String url = String.format(INTERVIEWER_CROSSED_TIMESLOTS.getEndpoint(), userId, userId2);
        return new ApacheHttpConnector().httpGet(url);
    }

}
