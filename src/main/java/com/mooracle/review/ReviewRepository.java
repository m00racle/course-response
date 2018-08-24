package com.mooracle.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

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

    /*
    * In order to set the authorized parties which has access to delete reviews we use Spring annotations
    * that is @PreAuthorized and pass parameters using SpEL or Spring Expression Language.
    *
    * All that inside the double quotes after @PreAuthorize is written using SpEL, make sure to check the notes
    *
    * One more thing since ReviewRepository class is available for injection using @Autowired it can be called
    * directly using SpEL using POJO standard naming reviewRepository.
    *
    * In this first delete is referenced by id, in this case this id is the ID of the review object. Thus we
    * need to fetch the username of the reviewer and check if it is the same as in the authentication header.
    *
    * Or is the user logged in hasRole AS ROLE_ADMIN
    * */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.reviewer?.username == authentication.name")/*<-speL check notes*/
    void delete(@Param("id") Long id);

    /*
    * On the second type of the delete it refers directly to the Review entity object. Thus we just fetch that
    * given object and fetch the username to be matched with the authentication username. OR if the user hasRole
    * ROLE_ADMIN
    * */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #review.reviewer?.username == authentication.name")/*<-spel*/
    void delete(@Param("review") Review entity);

    /*
    * NOTE: There are many other useful SpEL expressions please check the TEACHER'S NOTES*/
}
