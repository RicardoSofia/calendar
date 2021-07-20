package com.api.calendar.unittests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.mappers.CalendarMapper;
import com.api.calendar.data.mappers.UserMapper;
import com.api.calendar.repository.CalendarRepository;
import com.api.calendar.repository.UserRepository;
import com.api.calendar.service.UserService;
import com.api.calendar.testapi.test.TestSourceUsers;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest extends TestSourceUsers {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CalendarRepository calendarRepository;
    @Mock
    private UserMapper userMapper;

    @Mock
    private CalendarMapper calendarMapper;

    @Test
    public void getUserByIdTest() throws NotFoundException {
        doReturn(Optional.of(userInesDb)).when(userRepository).findById(any());
        doReturn(userInesDTO).when(userMapper).mapUserDBToUserDTO(userInesDb);
        UserDTO userByIdDTO = userService.getUserById(1);

        Assertions.assertEquals(userInesDTO.getUserName(), userByIdDTO.getUserName());
    }

    @Test
    public void getUserByIdNotFound() {
        doReturn(Optional.empty()).when(userRepository).findById(any());

        Assertions.assertThrows(NotFoundException.class,
            () -> userService.getUserById(1));
    }

    @Test
    public void updateUserById() {
        doReturn(userInesDb).when(userMapper).mapUserDTOToUserDB(any());
        doReturn(userInesDb).when(userRepository).save(any());

        userService.updateUser(userInesDTO);
    }

    @Test
    public void getUserCalendarTest() {
        doReturn(calendarDbList).when(calendarRepository).getInterviewCalendarById(1);
        doReturn(calendarDtoList).when(calendarMapper).mapListCalendarDBToListCalendarDTO(calendarDbList);

        List<CalendarTimeslotDTO> userCalendar = userService.getUserCalendar(1);

        Assertions.assertEquals(calendarDtoList, userCalendar);

    }

    @Test
    public void bookUserCalendarSlotsTest() throws NotFoundException {

        doReturn(Optional.of(userCandidateDb)).when(userRepository).findById(any());
        doReturn(calendarDbList).when(calendarMapper).mapListCalendarDTOToListCalendarDB(calendarDtoList);
        doReturn(userCandidateDb).when(userRepository).save(userCandidateDb);
        doReturn(userCandidateDTO).when(userMapper).mapUserDBToUserDTO(userCandidateDb);

        UserDTO userDTO = userService.bookUserCalendarSlots(3, calendarDtoList);

        Assertions.assertEquals("candidate", userDTO.getUserName());
        Assertions.assertEquals(1, userDTO.getInterviewCalendar().size());
    }

    @Test
    public void getUsersCrossedCalendarTest() {

        doReturn(calendarDbList).when(calendarRepository).getInterviewCalendarById(1);
        doReturn(calendarDtoList).when(calendarMapper).mapListCalendarDBToListCalendarDTO(calendarDbList);
        doReturn(calendarDbList2).when(calendarRepository).getInterviewCalendarById(2);
        doReturn(calendarDtoList2).when(calendarMapper).mapListCalendarDBToListCalendarDTO(calendarDbList2);

        List<CalendarTimeslotDTO> usersCrossedCalendar = userService.getUsersCrossedCalendar(1, 2);

        Assertions.assertEquals(2, usersCrossedCalendar.size());

    }
}
