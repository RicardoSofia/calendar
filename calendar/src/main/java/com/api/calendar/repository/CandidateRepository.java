package com.api.calendar.repository;

import com.api.calendar.entity.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Integer, Candidate> {

}
