package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.dao.BookRepository;
import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


/* I've tasked BookService with:
1. Scanning the todo book directory for books and
storing the list in the database and
then retrieving all books from the database and
telling BookManager to add each one to it's list
2. Reading the database's books table and creating a List of Books via BookManager
3. Saving new books to the database
4. Adding books to BookManager's List via BookManager
5. Deleting books via BookManager -> Mark it and move it (for now)
"Books marked for deletion are now missing from DELETE directory, Delete from database?"
6. Retrieving a book via BookManager
7. Retrieving all books via BookManager
8. Updating a book's data via BookManager
*/

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    // == Fields ==
    @Getter
    private final BookManager bookManager = new BookManager();

    @Autowired
    DataSource dataSource;  // DataSource config is in application.properties
    // Why shouldn't this class be instantiated in BookRepositoryImpl or something
    // This is not used here

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMetadataExtractor bookMetadataExtractor;


    // == Constructor ==

    public BookServiceImpl() {

    }


    // == Public methods ==


    /* Read Book Directory,
    get meta data for each book,
    create each book,
    save each book each to DB (and return DB row),
    add each returned row to BookManager
    */
    @Override
    public void loadBooksFromDirectory(File directory) {    // So maybe this should only load the books into directory


        for(final File fileEntry : directory.listFiles()){

            /*DEBUG*/ System.out.println(fileEntry.getName()+ "\t\t\t\t\t\t" + directory.getName()+"/"+ fileEntry.getName() + "\t" + LocalDate.now() + LocalTime.now());

            // Ignore directories
            if(!fileEntry.isDirectory()) {

                // Gather metadata from book
                Metadata metadata = bookMetadataExtractor.extractMetaData(fileEntry);

                // Create the book Todo This definitely doesn't seem safe
                Book newBook = new Book(
                        metadata.get("title")!=null? metadata.get("title"): fileEntry.getName(),
                        metadata.get("date")!=null? LocalDate.parse(metadata.get("date").substring(0,10)): LocalDate.parse("1799-12-31"),
                        fileEntry.getName(),
                        metadata.get("Author"),
                        LocalDateTime.now());


                // Save Book to DB. DB assigns ID.
                bookManager.addBook(bookRepository.save(newBook));
            }
        }
    }

    @Override
    public void addBook(Book newBook) {
        bookManager.addBook(newBook);
    }

    @Override
    public void deleteBook(int id) {
        //Put DELETION flag on book and send to DB
    }

    @Override
    public Book getBook(int id) {
        return bookManager.getBook(id);
    }

    @Override
    public void updateBook(Book updatedBook) {
        bookManager.updateBook(updatedBook);
    }


    // getBooksFromDb Todo
    // Troubleshooting 1. Delete all rows
    // Troubleshooting 2. Populate all rows from directory
    // 1. Gets books from books table
    // 2. Adds all books from DB to BookManager's list
    // 3. Returns the BookManager
    @Override
    public BookManager getBooksFromDb() {

        // Testcode
        //bookRepository.deleteAll();
        //loadBooksFromDirectory(new File("D:\\edu_repo\\ebooks\\"));

        Iterable<Book> bookIterable = bookRepository.findAll();

        for(Book book: bookIterable){
            bookManager.addBook(book);
        }

        return bookManager;
    }


    @Override
    public List<Book> getBooksFromMemory() {
        return bookManager.getBooksFromMemory();
    }
}


























