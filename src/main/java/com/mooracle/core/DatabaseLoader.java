package com.mooracle.core;

import com.mooracle.course.Course;
import com.mooracle.course.CourseRepository;
import com.mooracle.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class only being made to make a data loaded into the database. Using the ApplicationRunner interface from
 * Spring boot library.
 *
 * To do this we need to initialize a data obect repository in this case CourseRepository but we want it injected thus
 * we need @Autowired to do this.
 * */

@Component //<- to ensure this is treated as component when Application.java runs and launch Spring boot
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courses;

    @Autowired //<- this is not accepted directly since it was final need to be constructed as parameter
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    /**
     * Since we already have repository injected using Autowired we can just use it and pass the newly created
     * course object we just created
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics", "https://teamtreehouse.com/library/java-basics"); /*<-
        creating new Course object to be saved into database
        */

        course.addReview(new Review(3, "Not so good!!"));/*<- we add new review to this course*/

        courses.save(course); /*<- Save the newlyt created Course object into repository*/

        /*Now we want to add multiple courses and reviews
        *
        * first we prepare new courses names
        * */
        String[] templates = {/* we will make templates and randomly chosen from 100 possibilities later*/
                "Up and Running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Dummies",
                "Under the hood: %s"
                /* Note these names are usually a title of online Courses with %s will be substituted with course
                subject*/
        };
        String[] courseSubjects = {/* this array consist of course subject that will be paired with templates*/
                "Spring REST Data",
                "Java 9",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring HATEOAS" /* <- this is added later to differentiate the modulo between templates and subjects*/
        };

        List<Course> bunchOfCourses = new ArrayList<>(); /* set the platform for multiple course first*/
        IntStream.range(0,100) /*produce integer from 0 to 100*/
            .forEach(i -> {/* for each integer do this lambda!*/
                String template = templates[i % templates.length]; /*choose one from modulo means 0 to 4*/
                String courseSubject = courseSubjects[i % courseSubjects.length]; /* same as above principle*/
                String title = String.format(template, courseSubject); /*build a String title %s = courseSubject*/
                Course c = new Course(title, "http://www.example.com"); /*build new course using title and always
                the same example.com url*/
                c.addReview(new Review((i % 5) + 1 /*rating from 1 to 5*/,
                        String.format("More %s please!!", courseSubject) /*review message composed from it*/));
                bunchOfCourses.add(c); /*add each newly created Course c to bunchOfCourses List*/
            });
        courses.save(bunchOfCourses); /*save all bunchOfCourses to courses repository*/

    }
}
