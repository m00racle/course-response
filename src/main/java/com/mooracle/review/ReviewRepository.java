package com.mooracle.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Similar with CourseRepository read more there for more explonation
 * As usual this will extends the CRUD repository type super class which accepts Generics, so we will pass Course with
 * ID type of Long. Then all Create, Read, Update and Delete functionaliuty all injected into this interface
 * automatically
 *
 * We change our extended interface from CrudRepository to PagingAndSortingRepository to add paging and sorting
 * features.
 * */

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
}
