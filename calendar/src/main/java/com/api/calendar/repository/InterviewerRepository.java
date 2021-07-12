package com.api.calendar.repository;

import com.api.calendar.entity.Interviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends CrudRepository<Integer, Interviewer> {

}
