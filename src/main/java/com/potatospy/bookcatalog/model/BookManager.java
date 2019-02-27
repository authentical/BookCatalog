package com.potatospy.bookcatalog.model;


import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

@Slf4j
public class BookManager {


    // == Fields ==
    //
    private final List<Book> books = new ArrayList<>(); // List of all books


    // == Public Methods ==

    // Return BookManager's list of books
    public List<Book> getBooksFromMemory(){ return Collections.unmodifiableList(books); }

    // Return a Book with id from BookManager's List
    public Book getBook(Integer id){    // Todo: This can currently return a null book!!!!!!!!!!!!!!!!!!

        for(Book book: books){
            if(book.getId().equals(id)){
                log.info("\n\n\n####################\nGot book "+ book.getBookTitle());
                return book;
            }
        }

        return null;
    }

    // Add a Book to BookManager's List
    public void addBook(@NotNull Book newBook){

        books.add(newBook);


        /* If book manager is keeping track of the idValue = book_index
        and the database is auto-incrementing for every book added...
        why am I tracking idValue?
        Looks like idValue is only used in addBooks.

        What happens when a user adds a book manually?
        Create the book in DB and then call getBooksFromDb


        */
    }

    // Mark book for deletion and update book list
    public void deleteBook(Book bookToDelete){

        bookToDelete.setMarkedForDeletion(true);

        updateBook(bookToDelete);
        log.info("deleteBook called");
    }

    // Remove all books from memory
    public void deleteAllBooks(){

        books.clear();
    }

    // Replace Book in BookManager's List with the updated Book
    public void updateBook(@NotNull Book bookToUpdate){

        ListIterator<Book> bookListIterator = books.listIterator();

        while(bookListIterator.hasNext()){
            Book book = bookListIterator.next();

            if(book.getId().equals(bookToUpdate.getId())){

                bookListIterator.set(bookToUpdate);
                break;
            }
        }
    }



}
