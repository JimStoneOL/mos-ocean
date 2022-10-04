package com.joverlost.ejournal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String teacher;
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
