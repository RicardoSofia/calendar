package com.api.calendar.service;


import static com.api.calendar.validators.PredicateUtils.formatEndDate;
import static com.api.calendar.validators.PredicateUtils.formatStartDate;
import static com.api.calendar.validators.PredicateUtils.localDateIsValidMinute;
import static com.api.calendar.validators.PredicateUtils.localDateTimeLocalDateTimeBiPredicate;

import com.api.calendar.dto.CalendarDTO;
import com.api.calendar.dto.InterviewerDto;
import com.api.calendar.dto.UserDTO;
import com.api.calendar.entity.UserDb;
import com.api.calendar.mappers.UserMapper;
import com.api.calendar.repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleInterviewerService implements ScheduleCalendarInterface {

    private UserRepository userRepository;
    private UserMapper userMapper;

    private List<CalendarDTO> getTimeslotsFromRangeDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<CalendarDTO> listCalendarDto = new ArrayList<>();

        if(!localDateIsValidMinute.test(startDateTime.getMinute())) {
            startDateTime = formatStartDate.apply(startDateTime);
        }

        if(!localDateIsValidMinute.test(endDateTime.getMinute())) {
            endDateTime = formatEndDate.apply(endDateTime);
        }


        while(localDateTimeLocalDateTimeBiPredicate.test(startDateTime, endDateTime)) {
            CalendarDTO calendarDTO1 = new CalendarDTO(null, Timestamp.valueOf(startDateTime));
            listCalendarDto.add(calendarDTO1);
            startDateTime = startDateTime.withHour(startDateTime.getHour() + 1);
        }

        return listCalendarDto;
    }

    @Override
    public void ScheduleInterviewerTimeslots(InterviewerDto interviewerDto)
        throws NotFoundException {

        List<CalendarDTO> timeslotsFromRangeDate = getTimeslotsFromRangeDate(
            interviewerDto.getStartCalendar(), interviewerDto.getEndCalendar());

        UserDTO userDTO = getUserById(interviewerDto.getId());

        userDTO.getInterviewCalendar().addAll(timeslotsFromRangeDate);

        updateUser(userDTO);
    }

    @Override
    public UserDTO getUserById(Integer userId) throws NotFoundException {
        Optional<UserDb> userDb = userRepository.findById(userId);

        UserDb userDb1 = userDb.orElseThrow(NotFoundException::new);

        return userMapper.mapUserDBToUserDTO(userDb1);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserDb userDb = userMapper.mapUserDTOToUserDB(userDTO);

        userRepository.save(userDb);
    }
}
