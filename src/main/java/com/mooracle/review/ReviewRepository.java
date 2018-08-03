package com.mooracle.review;

import org.springframework.data.repository.CrudRepository;

/**
 * Similar with CourseRepository read more there for more explonation
 * As usual this will extends the CRUD repository type super class which accepts Generics, so we will pass Course with
 * ID type of Long. Then all Create, Read, Update and Delete functionaliuty all injected into this interface
 * automatically
 * */

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
