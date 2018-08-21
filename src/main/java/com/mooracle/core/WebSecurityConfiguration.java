package com.mooracle.core;

import com.mooracle.user.DetailsService;
import com.mooracle.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * THIS IS WHERE WE are going to Override the Spring Security to use our com.mooracle.user.DetailsService instead
 * naming on this class does not matter but this will help other developer to seek it.
 * */

@Configuration/*<- let Spring understand this is config file*/
@EnableWebSecurity/*< let spring esecurity know*/
@EnableGlobalMethodSecurity(prePostEnabled = true)/*<- allow us to secure our methods*/
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DetailsService userDetailsService;/*<- calling the details service*/

    /*Next we make the Spring Security override the authentication manager to our newly built
    com.mooracle.user.DetailsService:
    */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*This is going to pass in an authentication manager builder*/

        auth.userDetailsService(userDetailsService)/*<-careful auth also has userDetailsService as method*/

                .passwordEncoder(User.PASSWORD_ENCODER);/*<- this is point out our User's password encoder thus if
                we ever change it in the future we don't have to change this code also*/
    }

    /*Next we will override the HTTP security method:
    * */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*this will also use some sort of chainable sort of fluid API
        * */

        http
                .authorizeRequests()

                    .anyRequest().authenticated()/*<-any request must be authenticated*/

                .and()
                    .httpBasic()/*<- we want to use http basic auth (not recommended)*/

                .and()
                    .csrf().disable();

                /*Note in this session we disable the cross site reference forgery but this is not recommended
                * this is only so we can test in and out easily.
                * Developer will be able to tweak a little code here thanks to the power of abstraction all code
                * that we have built all along will still works.*/
    }
}
