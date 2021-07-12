package com.api.calendar.controller;

import com.api.calendar.dto.CandidateDTO;
import com.api.calendar.dto.InterviewerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interviewCalendar")
@AllArgsConstructor
public class InterviewCalenderController {

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("teste")
    public ResponseEntity<CandidateDTO> getCandidateCalendar(@RequestParam String candidateId){

        return ResponseEntity.ok(new CandidateDTO());
    }

    @GetMapping("/interviewer/{interviewerId}")
    @PreAuthorize("teste")
    public ResponseEntity<InterviewerDTO> getInterviewerCalendar(@RequestParam String interviewerId){

        return ResponseEntity.ok(new InterviewerDTO());
    }
}
