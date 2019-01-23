package com.potatospy.bookcatalog.model;

import com.sun.istack.NotNull;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class BookManager {

    // == Fields ==

    private final List<Book> books = new ArrayList<>(); // List of all books


    // == Constructor ==

    public BookManager(){
        /*
        * Still testing using Tika library to try to get
        * metadata out of the ebooks automatically
        * Todo: Implement or find another method
         */

        // //dummy data for testing
        Metadata metadata = new Metadata();
        Tika tika = new Tika();

        final File folder = new File("D:\\edu_repo\\ebooks\\");
        for(final File fileEntry : folder.listFiles()){

            //addBook(new Book(fileEntry.getName(), folder.toString(), LocalDate.now());
            System.out.println(fileEntry.getName()+ "\t\t\t\t\t\t" + folder.getName()+"/"+ fileEntry.getName() + "\t" + LocalDate.now() + LocalTime.now());
        }
    }


    // == Public Methods ==

    public List<Book> getBooks(){ return Collections.unmodifiableList(books); }

    public Book getBook(int id){    // Todo: This can currently return a null book!!!!!!!!!!!!!!!!!!

        for(Book book: books){
            if(book.getId() == id){
                return book;
            }
        }

        return null;
    }

    public void scanBookDirectory(File directory){

        // Todo dummy for testing
        Metadata metadata = new Metadata();     // duplicate code
        Tika tika = new Tika();                 // duplicate code

        final File folder = new File("D:\\edu_repo\\ebooks\\"); // duplicate code
        for(final File fileEntry : folder.listFiles()){

            //addBook(new Book(fileEntry.getName(), fileEntry.toString(), LocalDate.now(),null,"",metadata.get(Metadata.AUTHOR) ));
        }
        // String bookTitle, String isbn, Date publishedDate, String fileType, String edition, String publisher, String authors)

    }


    public void addBook(@NotNull Book newBook){

        books.add(newBook);


        /* If book manager is keeping track of the idValue = book_index
        and the database is auto-incrementing for every book added...
        why am I tracking idValue?
        Looks like idValue is only used in addBooks.

        What happens when a user adds a book manually?
        Create the book in DB and then call getBooks


        */
    }

    // Todo: deleteBook

    public void updateBook(@NotNull Book bookToUpdate){

        ListIterator<Book> bookListIterator = books.listIterator();

        while(bookListIterator.hasNext()){
            Book book = bookListIterator.next();

            if(book.equals(bookToUpdate)){

                bookListIterator.set(bookToUpdate);
                break;
            }
        }
    }






























}
