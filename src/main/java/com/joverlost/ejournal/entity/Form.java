package com.joverlost.ejournal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String middleName;
    private String position;
    private String division;
    private String email;
    private int booking;
    private boolean accepted;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private EventTime eventTime;
}