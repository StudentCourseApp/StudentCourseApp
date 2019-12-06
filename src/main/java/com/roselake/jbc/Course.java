package com.roselake.jbc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Student> students;

    public Course() { }

    public Course(@NotNull @Size(min = 3) String name,
                  @NotNull @Size(min = 3) String description,
                  @NotNull @Size(min = 3) String faculty) {
        this.name = name;
        this.description = description;
        this.faculty = faculty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
