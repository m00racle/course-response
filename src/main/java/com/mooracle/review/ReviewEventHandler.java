package com.mooracle.review;

import com.mooracle.user.User;
import com.mooracle.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sun.plugin.liveconnect.SecurityContextHelper;

/*
* This class is created to handle events related to Review @Entity creation
* In this session we will make the logged in user as the reviewer as the review @Entity is being created
* */
@Component
@RepositoryEventHandler(Review.class)/*<- this annotation will take this class and whatever its' contained to watch*/
public class ReviewEventHandler {
    private final UserRepository users;/*<- this will be @Autowired in the constructor not here coz its final*/

    @Autowired/*<- this is autowired*/
    public ReviewEventHandler(UserRepository users) {
        this.users = users;
    }

    /*Try to add reviewer as we go:
    * */
    @HandleBeforeCreate/*<- it will be run before the CRUD create process of the review*/
    public void addReviewerBasedOnLoggedInUser(Review review){
        String username = SecurityContextHolder
                            .getContext().getAuthentication()
                                .getName();/*<- this is how we get the username of the logged in user*/

        User user = users.findByUsername(username);/*<- here we try to find the User @Entity based on the username*/

        review.setReviewer(user);/*<- set the user as the reviewer of the review*/
    }
}
