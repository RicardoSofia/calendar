package com.api.calendar.repository;

import com.api.calendar.entity.CalendarDb;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends CrudRepository<CalendarDb, Integer> {

    @Query(value = "SELECT cal FROM CalendarDb AS cal " +
        "LEFT JOIN UserDb AS user ON user.id = cal.user.id " +
        "WHERE user.id = :userId "
    )
    List<CalendarDb> getInterviewCalendarById(Integer userId);

}
