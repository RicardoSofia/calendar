package com.api.calendar.repository;

import com.api.calendar.entity.CalendarDb;
import com.api.calendar.entity.UserDb;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDb, Integer> {


    @Override
    Optional<UserDb> findById(Integer userId);

//    @Query(value = "SELECT cal FROM CalendarDB AS cal " +
//        "LEFT JOIN UserDB AS user ON user.id = cal.user.id " +
//        "WHERE user.id = :userId "
//    )
//    List<CalendarDb> getInterviewCalendarById(Integer userId);

//    List<CalendarDb> getInterviewCalendarWhereCalendarDbs

    UserDb findByUserName(String userName);
}
