package com.api.calendar.controller;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.UserDTO;
import com.api.calendar.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl.Use;
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

@RestController
@RequestMapping("/interviewCalendar")
@AllArgsConstructor
public class InterviewCalenderController {

    private UserService userService;

    @DeleteMapping("/clear")
    public ResponseEntity clearUsers() {
        userService.clearUsers();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/creation")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws NotFoundException {

        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/candidate/{candidateId}")
//    @PreAuthorize("teste")
    public ResponseEntity<UserDTO> getCandidate(@PathVariable Integer candidateId)
        throws NotFoundException {
        UserDTO candidateDto = userService.getUserById(candidateId);
        return ResponseEntity.ok(candidateDto);
    }

    @GetMapping("/candidate/{candidateId}/timeslots")
    public ResponseEntity<CalendarDTO> getCandidateBookedTimeslot(@PathVariable Integer candidateId) {
        List<CalendarDTO> userCalendar = userService.getUserCalendar(candidateId);
        return ResponseEntity.ok(userCalendar.get(0));
    }

    @GetMapping("/interviewer/{interviewerId}")
//    @PreAuthorize("teste")
    public ResponseEntity<UserDTO> getInterviewer(@PathVariable Integer interviewerId)
        throws NotFoundException {
        UserDTO interviewerDto = userService.getUserById(interviewerId);
        return ResponseEntity.ok(interviewerDto);
    }

    @GetMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity<List<CalendarDTO>> getInterviewerTimeslots(@PathVariable Integer interviewerId) {
        List<CalendarDTO> userCalendar = userService.getUserCalendar(interviewerId);
        return ResponseEntity.ok(userCalendar);
    }

    @GetMapping("/interviewer/{interviewerId}/{interviewerId2}/crossedTimeslots")
    public ResponseEntity<List<CalendarDTO>> getInterviewersCrossedTimeslots(@PathVariable Integer interviewerId, @PathVariable Integer interviewerId2) {
        List<CalendarDTO> usersCrossedCalendar = userService
            .getUsersCrossedCalendar(interviewerId, interviewerId2);

        return ResponseEntity.ok(usersCrossedCalendar);
    }

    @PostMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity updateInterviewer(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/interviewer/{interviewerId}/timeslots")
    public ResponseEntity updateInterviewerPut(@PathVariable Integer interviewerId, @RequestBody List<CalendarDTO> calendarDTOList)
        throws NotFoundException {
        return ResponseEntity.ok(userService.bookUserCalendarSlots(interviewerId, calendarDTOList));
    }

    @PostMapping("/candidate/{candidateId}/timeslots")
    public ResponseEntity sendCandidateTimeslot(@PathVariable Integer candidateId, @RequestBody UserDTO candidateDto) {
        userService.updateUser(candidateDto);
        return ResponseEntity.ok().build();
    }
}
