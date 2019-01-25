package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;

import java.io.File;
import java.util.List;


public interface BookService {

    void readBookDirectory(File directory);   // Scan file system book directory and
                                // add books to database

    void addBook(Book newBook); // Add a single book to the database.

    void deleteBook(int id);    // This should only be marking the book
                                // for deletion so BookManager can move the
                                // file

    Book getBook(int id);       // returns a book

    void updateBook(Book updatedBook);  // Updates a book

    BookManager getBooksFromDb();     // returns all books

    List<Book> getBooksFromMeory();

}
