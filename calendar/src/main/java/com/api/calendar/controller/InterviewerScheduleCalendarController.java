package com.api.calendar.controller;

import com.api.calendar.data.dto.InterviewerScheduleDto;
import com.api.calendar.service.ScheduleInterviewerService;
import com.api.calendar.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduleCalendar")
@AllArgsConstructor
public class InterviewerScheduleCalendarController {

    private UserService userService;
    private ScheduleInterviewerService scheduleInterviewerService;

    @ApiOperation(value = "Post interviewer start and end date",
        notes = "<p>Post interviewer start and end date.</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity postInterviewerSchedule(@RequestBody @Valid InterviewerScheduleDto interviewerScheduleDto)
        throws NotFoundException {

        scheduleInterviewerService.ScheduleInterviewerTimeslots(interviewerScheduleDto);
        return ResponseEntity.ok().build();
    }

}
