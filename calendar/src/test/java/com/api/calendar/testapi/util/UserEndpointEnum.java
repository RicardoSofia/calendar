package com.api.calendar.testapi.util;

public enum UserEndpointEnum {
    MAIN_URL("http://127.0.0.1:8080"),
    USER_ENDPOINT(MAIN_URL.getEndpoint() + "/interviewCalendar"),
    SCHEDULE_ENDPOINT(MAIN_URL.getEndpoint() + "/scheduleCalendar"),
    USER_CREATION(USER_ENDPOINT.getEndpoint() + "/creation"),
    USER_CLEAR(USER_ENDPOINT.getEndpoint() + "/clear"),
    INTERVIEWER_ENDPOINT(USER_ENDPOINT.getEndpoint() + "/interviewer/%s"),
    CANDIDATE_ENDPOINT(USER_ENDPOINT.getEndpoint() + "/candidate/%s"),
    INTERVIEWER_TIMESLOTS(INTERVIEWER_ENDPOINT.getEndpoint() + "/timeslots"),
    INTERVIEWER_CROSSED_TIMESLOTS(INTERVIEWER_ENDPOINT.getEndpoint() + "/%s/crossedTimeslots"),
    CANDIDATE_TIMESLOTS(CANDIDATE_ENDPOINT.getEndpoint() + "/timeslots");

    private final String endpoint;

    UserEndpointEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
