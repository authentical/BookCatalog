package com.potatospy.bookcatalog.dao;

import com.potatospy.bookcatalog.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Optional;

// @Repository "a mechanism for encapsulating storage, retrieval,
// and search behavior which emulates a collection of objects"
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    //////////////////////////////////////////////////////////
    // Interface for the application to CRUD on Books in DB //
    /*

    DAO should be limited to only add/update/insert/select
    Entity objects into/from database and that's all.  OK


    //
    //          Method's available from CrudRepository
    //
    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    */
}
