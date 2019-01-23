package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;
import lombok.Getter;

import java.io.File;
import java.util.List;

public class BookServiceImpl implements BookService{

    // == Fields ==
    @Getter
    private final BookManager bookManager = new BookManager();


    // == Public methods ==
    @Override
    public void scanBookDirectory(File directory) {
        bookManager.scanBookDirectory(directory);
    }

    @Override
    public void addBook(Book newBook) {
        bookManager.addBook(newBook);
    }

    @Override
    public void deleteBook(int id) {

    }

    @Override
    public Book getBook(int id) {
        return bookManager.getBook(id);
    }

    @Override
    public void updateBook(Book updatedBook) {
        bookManager.updateBook(updatedBook);
    }

    @Override
    public List<Book> getBooks() {
        return bookManager.getBooks();
    }
}
