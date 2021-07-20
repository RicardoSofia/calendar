package com.api.calendar.service.interfaces;

import com.api.calendar.data.dto.UserDTO;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserInterface {

    UserDTO getUserById(Integer userId) throws NotFoundException;

    void updateUser(UserDTO userDTO);
}
