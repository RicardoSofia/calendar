package com.api.calendar.repository;

import com.api.calendar.data.entity.UserDb;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDb, Integer> {

    UserDb findByUserName(String userName);
}
