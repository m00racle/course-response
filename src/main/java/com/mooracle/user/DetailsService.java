package com.mooracle.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * This class implements UserDetailsService interface which must be implemented by developer according to their
 * specific environment thus Spring Security only provides interface for it
 *
 * the word user here is what Spring is calling our user not our @Entity User.java class
 * */

@Component /*<- thus wew can export it so other can find it when component scan in action*/
public class DetailsService implements UserDetailsService {
    /*we need to get a hold of our user thus we need to initialize our user repo
    * */
    @Autowired
    UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Again this is not our com.mooracle.user.User but the user and if it cannot be found we must
        * throw exception. If it found it will be defined as User Object:
        * */

        User user = users.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(username + " was not found");/*<- this is the error message*/
        }
        return new org.springframework.security.core.userdetails.User(/*<- this is User not from our package*/
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles())/*<- this is the default form of auth list
                getRoles here is what we need to provide*/
        );
    }
}
