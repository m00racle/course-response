package com.mooracle.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * After creating the User Entity we then add CRUD database capabilities into it to form a repository database for
 * users. However if we just keep it empty it will only comes using the extended CrudRepository class and it is
 * not our best intention. We certainly do not want anybody to access the user database and steal all of the
 * information in it even if the password field is encrypted.*/

@RepositoryRestResource(exported = false)/*<- this annotation will make this repository to exist in our API thus
there will be no /users URI on our API*/
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
