package com.api.calendar.data.mappers;

import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.entity.UserDb;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

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
