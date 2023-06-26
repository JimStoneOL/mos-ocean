package com.joverlost.ejournal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EventDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate localDate;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Event event;
    @OneToMany(mappedBy = "eventDate")
    private List<EventTime> eventTimeList;
}
