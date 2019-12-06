package com.roselake.jbc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=3)
    private String name;

    @NotNull
    @Size(min=3)
    private String description;

    @NotNull
    @Size(min=3)
    private String faculty;

    @ManyToMany
    private Set<Student> students;


}
