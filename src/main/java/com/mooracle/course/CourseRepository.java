package com.mooracle.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Because this is By Feature Approach this repository type interface is located inside the course package
 * As usual this will extends the CRUD repository type super class which accepts Generics, so we will pass Course with
 * ID type of Long. Then all Create, Read, Update and Delete functionaliuty all injected into this interface
 * automatically
 *
 * We swithch the extended interface from CrudRepository to PagingAndSortingRepository to add paging and sorting
 * feature on our data in database.
 * */

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @RestResource(rel = "title-contains", path = "containsTitle")/* <- this will change the rel on the search page
    and the path will change the path when we give the query URi a key to search*/
    Page<Course> findByTitleContaining(@Param("title") String title, Pageable page); /* <- this will add the ability
    to search using title as parameter and paged ability to explore all page*/
}
