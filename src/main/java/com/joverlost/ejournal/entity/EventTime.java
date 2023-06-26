package com.joverlost.ejournal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private LocalTime localTime;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private EventDate eventDate;
    @OneToMany(mappedBy = "eventTime")
    private List<Form> forms;
}
