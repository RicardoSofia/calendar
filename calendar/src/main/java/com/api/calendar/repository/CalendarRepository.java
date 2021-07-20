package com.api.calendar.repository;

import com.api.calendar.data.entity.CalendarTimeslotDb;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends CrudRepository<CalendarTimeslotDb, Integer> {

    @Query(value = "SELECT cal FROM CalendarTimeslotDb AS cal " +
        "LEFT JOIN UserDb AS user ON user.id = cal.user.id " +
        "WHERE user.id = :userId "
    )
    List<CalendarTimeslotDb> getInterviewCalendarById(Integer userId);

}
