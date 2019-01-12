package com.potatospy.bookcatalog.dao;

import com.potatospy.bookcatalog.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// @Repository "a mechanism for encapsulating storage, retrieval,
// and search behavior which emulates a collection of objects"
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {


    // Interface for the application to CRUD on Books in DB

}
