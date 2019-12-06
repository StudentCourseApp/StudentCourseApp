package com.roselake.jbc;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<Student, Long> {

    ArrayList<Student> findByFirstNameContainsIgnoreCase(String firstName);

    ArrayList<Student> findByLastNameContainsIgnoreCase(String lastName);

}
