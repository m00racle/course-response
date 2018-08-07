package com.mooracle.review;

import com.mooracle.core.BaseEntity;
import com.mooracle.course.Course;

import javax.persistence.*;

@Entity
public class Review extends BaseEntity {

    private int rating;
    private String description;

    @ManyToOne /*<- no need to specify for this since it will have course as part of this just make sure we build
    getters and setters for this Course object called course*/
    private Course course;

    protected Review() {
        super();/* <- instead using full base constructor we now can just call super() to have the same constructor as
         id = null;
         This is handy since if we need to change any other part of the ID naming mechanism we can just change the
         abstract class com.mooracle.core.BaseEntity instead we go around change Course and Review class
         If we just want to add features to Id naming specific just for this class we can add it after the super()
         keyword
         NOTE: super class and parent class are interchangably used
         */
    }

    /*Since we already wired the review to Course class we need to construct the review so that when users want to add
    * a Review it was ready to be initialized
    * This is different compared to default constructor above since it will require parameters to be passed
    * */
    public Review(int rating, String description) {
        this.rating = rating;
        this.description = description;
        /*course is not here since right now it will be initialized in the com.mooracle.core.DatabaseLoader as part
        * of a Course which we will have setCourse here
        * When course.addReview method is called it will launch the Review constructor to ask rating and description
        * parameter of the review*/
    }

    /*we build getters and setters for course*/
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
