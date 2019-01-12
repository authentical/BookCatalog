package com.potatospy.bookcatalog.dao;

import com.potatospy.bookcatalog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    // Interface for the application to CRUD on Users in DB

}
