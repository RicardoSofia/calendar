package com.api.calendar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserDb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @NotNull
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<CalendarDb> interviewCalendar = new ArrayList<>();

}
