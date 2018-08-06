package com.mooracle.course;

import com.mooracle.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // this a model of an Object hence an Entity in JPA
public class Course extends BaseEntity {


    private String title;
    private String url;

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
