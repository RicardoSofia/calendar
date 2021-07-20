package com.api.calendar.service;


import static com.api.calendar.validators.PredicateUtils.formatEndDate;
import static com.api.calendar.validators.PredicateUtils.formatStartDate;
import static com.api.calendar.validators.PredicateUtils.localDateIsValidMinute;
import static com.api.calendar.validators.PredicateUtils.localDateTimeLocalDateTimeBiPredicate;

import com.api.calendar.data.dto.CalendarTimeslotDTO;
import com.api.calendar.data.dto.InterviewerScheduleDto;
import com.api.calendar.data.dto.UserDTO;
import com.api.calendar.data.entity.UserDb;
import com.api.calendar.data.mappers.UserMapper;
import com.api.calendar.repository.UserRepository;
import com.api.calendar.service.interfaces.ScheduleCalendarInterface;
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

    private List<CalendarTimeslotDTO> getTimeslotsFromRangeDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<CalendarTimeslotDTO> listCalendarDto = new ArrayList<>();

        if(!localDateIsValidMinute.test(startDateTime.getMinute())) {
            startDateTime = formatStartDate.apply(startDateTime);
        }

        if(!localDateIsValidMinute.test(endDateTime.getMinute())) {
            endDateTime = formatEndDate.apply(endDateTime);
        }


        while(localDateTimeLocalDateTimeBiPredicate.test(startDateTime, endDateTime)) {
            CalendarTimeslotDTO calendarDTO1 = new CalendarTimeslotDTO(null, Timestamp.valueOf(startDateTime));
            listCalendarDto.add(calendarDTO1);
            startDateTime = startDateTime.withHour(startDateTime.getHour() + 1);
        }

        return listCalendarDto;
    }

    @Override
    public void ScheduleInterviewerTimeslots(InterviewerScheduleDto interviewerScheduleDto)
        throws NotFoundException {

        List<CalendarTimeslotDTO> timeslotsFromRangeDate = getTimeslotsFromRangeDate(
            interviewerScheduleDto.getStartCalendar(), interviewerScheduleDto.getEndCalendar());

        UserDTO userDTO = getUserById(interviewerScheduleDto.getId());

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
