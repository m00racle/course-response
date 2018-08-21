package com.mooracle.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mooracle.core.BaseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

/**
 * This class was added to add user as authentication feature into our API. This was added after the modification
 * of the build.gradle file to add spring boot security dependencies. This class will also extends
 * com.mooracle.core.BaseEntity abstract class to automatically add auto generated id and also versioning feature
 * */

@Entity /*<- this will become an entity which create many user object*/
public class User extends BaseEntity {
    private String username;
    private String firstName;
    private String lastName;
    @JsonIgnore /*<- this will ensure it will not be imported into Json data to be shown but it still not enough*/
    private String password;
    @JsonIgnore/*<- this list the role the user has, one user can have more than one role also ignored by JSON*/
    private String[] roles;

    /*TO ENSURE PASSWORD IS PROPERLY ENCRYPTED we need to add Password Encoder into the API
    * This is part of the spring boot security and we choose to use BCrypt Password Encoder
    * we make it static thus it will be instantiated everytime a User is instantiated*/
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);/*<- we make setter for the password which take the
        String password and encrypt it using PASSWORD_ENCODER static field*/
    }

    /*we make the constructor then but note that the password field constructor will call the setPassword method
    * and passed the String password argument*/

    public User(String username, String firstName, String lastName, String password, String[] roles) {
        this();/*<- same with other @Entity it needs to call the default constructor thus this() is calling User()*/
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        setPassword(password);/*<- this to ensure that password will always be encrypted*/
        this.roles = roles;
    }

    /*Also remember this is an entity thus we need to build default constructor which pass no argument*/
    protected User(){
        super();/*<- same as other @Entity it needs to call super*/
    }
    /*Other than the password let's make getters and setters on those fields*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        /*See that password this time only gets getter on its field since setter already set above to use the
        * encryption. Since it will be set to be encrypted during the setter period we can just make getter out
        * of it. It should be an encrypted character*/
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
