package com.roselake.jbc;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CourseRepository extends CrudRepository<Course, Long> {

    ArrayList<Course> findByNameContainsIgnoreCase(String name);

}
