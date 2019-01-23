package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.dao.BookRepository;
import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;
import lombok.Getter;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/* I've tasked BookService with:
1. Scanning the todo book directory for books and telling BookManager to add each one to it's list
2. Reading the database's books table and creating a List of Books via BookManager
3. Saving new books to the database
4. Adding books to BookManager's List via BookManager
5. Deleting books via BookManager -> Mark it and move it (for now)
"Books marked for deletion are now missing from DELETE directory, Delete from database?"
6. Retrieving a book via BookManager
7. Retrieving all books via BookManager
8. Updating a book's data via BookManager




*/


@Service
public class BookServiceImpl implements BookService{

    // == Fields ==
    @Getter
    private final BookManager bookManager = new BookManager();

    @Autowired
    DataSource dataSource;  // DataSource config is in application.properties
    // Why shouldn't this class be instantiated in BookRepositoryImpl or something
    // THis is not used here

    @Autowired
    BookRepository bookRepository;



    // == Public methods ==

    @Override
    public void scanBookDirectory(File directory) {

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

            Book newBook = new Book(fileEntry.getName(),folder.getName()+"/"+ fileEntry.getName(), LocalDate.now());
        }
        //bookManager.scanBookDirectory(directory);
    }

    @Override
    public void addBook(Book newBook) {
        bookManager.addBook(newBook);
    }

    @Override
    public void deleteBook(int id) {
        //
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
