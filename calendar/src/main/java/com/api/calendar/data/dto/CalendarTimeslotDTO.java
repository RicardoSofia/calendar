package com.api.calendar.data.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@NoArgsConstructor
@ToString
public class CalendarTimeslotDTO {

    private Integer id;

    private Timestamp dateTime;

}
