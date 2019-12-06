package com.roselake.jbc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=2)
    private String firstName;

    @NotNull
    @Size(min=2)
    private String lastName;

    @NotNull
    @Size(min=10)
    private String dob;     //"YYYY-MM-DD"

    @NotNull
    private String image;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Student() { this.image = ""; }

    // simple data loader constructor :: no default image
    public Student(@NotNull @Size(min = 2) String firstName,
                   @NotNull @Size(min = 2) String lastName,
                   @NotNull @Size(min = 10) String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.image = "";
    }

    // for data loader, with a default avatar
    public Student(@NotNull @Size(min = 2) String firstName,
                   @NotNull @Size(min = 2) String lastName,
                   @NotNull @Size(min = 10) String dob,
                   @NotNull String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
