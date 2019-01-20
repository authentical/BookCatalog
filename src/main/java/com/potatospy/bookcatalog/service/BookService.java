package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;

public interface BookService {

    void addBook(Book newBook);

    void deleteBook(int id);    // This should only be marking for the book
                                // for deletion so BookManager can move the
                                // file

    Book getBook(int id);

    void updateBook(Book updatedBook);

    BookManager getBooks();

}
