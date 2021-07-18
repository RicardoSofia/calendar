package com.api.calendar.mappers;

import com.api.calendar.dto.UserDTO;
import com.api.calendar.entity.UserDb;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDb mapUserDTOToUserDB(UserDTO userDTO);

    UserDTO mapUserDBToUserDTO(UserDb userDb);

    @AfterMapping
    default void setUserInRelation(@MappingTarget UserDb userDb) {
        userDb.getInterviewCalendar().forEach(interviewCalendar -> {
            interviewCalendar.setUser(userDb);
        } );
    }
}
