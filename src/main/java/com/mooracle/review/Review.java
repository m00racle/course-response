package com.mooracle.review;

import com.mooracle.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review extends BaseEntity {

    // TODO: we are duplicating with all @Entity
    private int rating;
    private String description;

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
