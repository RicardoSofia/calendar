package com.api.calendar.controller;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/interviewCalendar")
@AllArgsConstructor
public class InterviewCalenderController {

    private UserService userService;

    @DeleteMapping("/clear")
    @ApiOperation(value = "Clear users in database, used for testing",
        notes = "<p>Clears the users on the database.</p>"
            )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity clearUsers() {
        userService.clearUsers();

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Create users in database, used for testing",
        notes = "<p>Create user.</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/creation")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws NotFoundException {

        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @ApiOperation(value = "Get Candidate",
        notes = "<p>Get the candidate on the database, return candidate to schedule Interview.</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/candidate/{candidateId}")
//    @PreAuthorize("teste")
    public ResponseEntity<UserDTO> getCandidate(@PathVariable Integer candidateId)
        throws NotFoundException {
        UserDTO candidateDto = userService.getUserById(candidateId);
        return ResponseEntity.ok(candidateDto);
    }

    @ApiOperation(value = "Get user booked interview.",
        notes = "<p>Get the Booked timeslot the candidate choose for the interview.</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/candidate/{candidateId}/timeslots")
    public ResponseEntity<CalendarTimeslotDTO> getCandidateBookedTimeslot(@PathVariable Integer candidateId) {
        List<CalendarTimeslotDTO> userCalendar = userService.getUserCalendar(candidateId);
        return ResponseEntity.ok(userCalendar.get(0));
    }

    @ApiOperation(value = "Get the Interviewer",
        notes = "<p>Get Interviewer.</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/interviewer/{interviewerId}")
//    @PreAuthorize("teste")
    public ResponseEntity<UserDTO> getInterviewer(@PathVariable Integer interviewerId)
        throws NotFoundException {
        UserDTO interviewerDto = userService.getUserById(interviewerId);
        return ResponseEntity.ok(interviewerDto);
    }

    @ApiOperation(value = "Get Interviewer timeslots",
        notes = "<p>Get Interviewer Available timeslots</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity<List<CalendarTimeslotDTO>> getInterviewerTimeslots(@PathVariable Integer interviewerId) {
        List<CalendarTimeslotDTO> userCalendar = userService.getUserCalendar(interviewerId);
        return ResponseEntity.ok(userCalendar);
    }

    @ApiOperation(value = "Get Interviewers Timeslots availables for interviews",
        notes = "<p>Get interviewers timeslots for Interview</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/interviewer/{interviewerId}/{interviewerId2}/crossedTimeslots")
    public ResponseEntity<List<CalendarTimeslotDTO>> getInterviewersCrossedTimeslots(@PathVariable Integer interviewerId, @PathVariable Integer interviewerId2) {
        List<CalendarTimeslotDTO> usersCrossedCalendar = userService
            .getUsersCrossedCalendar(interviewerId, interviewerId2);

        return ResponseEntity.ok(usersCrossedCalendar);
    }

    @ApiOperation(value = "Post update timeslots",
        notes = "<p>Post update timeslots</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity updateInterviewer(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Put update timeslots",
        notes = "<p>Put update timeslots using list</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity updateInterviewerPut(@PathVariable Integer interviewerId, @RequestBody List<CalendarTimeslotDTO> calendarDTOList)
        throws NotFoundException {
        return ResponseEntity.ok(userService.bookUserCalendarSlots(interviewerId, calendarDTOList));
    }

    @ApiOperation(value = "post candidate choose timeslot",
        notes = "<p>Post candidate choose timeslot</p>"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 401, message = "Unauthorised"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/candidate/{candidateId}/timeslots")
    public ResponseEntity sendCandidateTimeslot(@PathVariable Integer candidateId, @RequestBody UserDTO candidateDto) {
        userService.updateUser(candidateDto);
        return ResponseEntity.ok().build();
    }
}
