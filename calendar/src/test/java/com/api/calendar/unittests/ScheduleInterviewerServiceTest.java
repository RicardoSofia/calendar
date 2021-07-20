package com.api.calendar.unittests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static reactor.core.publisher.Mono.when;

import com.api.calendar.data.dto.InterviewerScheduleDto;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.mappers.UserMapper;
import com.api.calendar.repository.UserRepository;
import com.api.calendar.service.ScheduleInterviewerService;
import com.api.calendar.testapi.test.TestSourceUsers;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ScheduleInterviewerServiceTest extends TestSourceUsers {

    @InjectMocks
    private ScheduleInterviewerService scheduleInterviewerService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Test
    public void getUserByIdTest() throws NotFoundException {
        doReturn(Optional.of(userInesDb)).when(userRepository).findById(any());
        doReturn(userInesDTO).when(userMapper).mapUserDBToUserDTO(userInesDb);
        UserDTO userByIdDTO = scheduleInterviewerService.getUserById(1);

        Assertions.assertEquals(userInesDTO.getUserName(), userByIdDTO.getUserName());
    }

    @Test
    public void getUserByIdNotFound() {
        doReturn(Optional.empty()).when(userRepository).findById(any());

        Assertions.assertThrows(NotFoundException.class,
            () -> scheduleInterviewerService.getUserById(1));
    }

    @Test
    public void updateUserById() {
        doReturn(userInesDb).when(userMapper).mapUserDTOToUserDB(any());
        doReturn(userInesDb).when(userRepository).save(any());

        scheduleInterviewerService.updateUser(userInesDTO);
    }

    @Test
    public void scheduleInterviewerTimeslots() throws NotFoundException {
        doReturn(Optional.of(userInesDb)).when(userRepository).findById(any());
        doReturn(userInesDTO).when(userMapper).mapUserDBToUserDTO(userInesDb);
        doReturn(userInesDb).when(userMapper).mapUserDTOToUserDB(any());
        doReturn(userInesDb).when(userRepository).save(any());

        InterviewerScheduleDto interviewerScheduleDtoCompleteValidTimeslots = new InterviewerScheduleDto(1,"ines" , todayNoon, todayPlus4);

        scheduleInterviewerService.ScheduleInterviewerTimeslots(interviewerScheduleDtoCompleteValidTimeslots);
    }

}
