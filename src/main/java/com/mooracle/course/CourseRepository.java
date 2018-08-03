package com.mooracle.course;

import org.springframework.data.repository.CrudRepository;

/**
 * Because this is By Feature Approach this repository type interface is located inside the course package
 * As usual this will extends the CRUD repository type super class which accepts Generics, so we will pass Course with
 * ID type of Long. Then all Create, Read, Update and Delete functionaliuty all injected into this interface
 * automatically
 * */

public interface CourseRepository extends CrudRepository<Course, Long> {

}
