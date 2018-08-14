package com.mooracle.course;

import com.mooracle.core.BaseEntity;
import com.mooracle.review.Review;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity // this a model of an Object hence an Entity in JPA
public class Course extends BaseEntity {

    @NotNull /*<- this is validation annotation of the entity must have non null title if want to process*/
    @Size(min = 2, max=140) /*<- this validator is to fix the bug of creating a course using empty string as course
    title and also to prevent over capacitate the course title field*/
    private String title;

    private String url;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;/*<- this will make relationship with one course to many Reviews but we need to set
    also the "course" side in the com.mooracle.review.Review to have many to one relation
    Note that we use list to make it many reviews to one course

    As for the cascade it will make if the course gets deleted the reviews inside also deleted!*/

    /*
    * The JPA requires a constructors that takes no parameter
    * Thus we will build it using a protected that only classes in this package can access it
    * */
    protected Course() {

        super();/* <- instead using full base constructor we now can just call super() to have the same constructor as
         id = null;
         This is handy since if we need to change any other part of the ID naming mechanism we can just change the
         abstract class com.mooracle.core.BaseEntity instead we go around change Course and Review class
         If we just want to add features to Id naming specific just for this class we can add it after the super()
         keyword
         NOTE: super class and parent class are interchangably used
         */

        reviews = new ArrayList<>(); /*<- this is where the added initialization is in action since we need this List
        of review initialized which is not available in the Review class. Then it will establish the relationship with
        the Review class*/
    }

    /*
    * We build also alternate constructor to be user friendly for setting the Course later
    * */

    public Course(String title, String url) {
        /*
        * At this point the dafault (protected) constructor already defined how the id should look like in
        * initialization thus we just called the default using this()
        * */
        this();

        this.title = title;
        this.url = url;
    }
    /*
    * Build Getter and Setters
    * */

    public List<Review> getReviews() {
        /*We only make getters for the review since we do not want users can set all the reviews because it will be bad
        * if users can just change all available reviews
        *
        * We need to add new public method that only allow users to add not to modify the reviews outside users review
        * */
        return reviews;
    }

    public void addReview(Review review){
        review.setCourse(this);/*<- this is important to set the relationship of this reveiew to this course.
        I forgot once and it fails to emerge on the database*/

        reviews.add(review); /*<- this will only add review (new review) to the existing List of reviews
        if no review ever added it will only initialized empty reviews list*/
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
