package com.potatospy.bookcatalog.service;

import com.potatospy.bookcatalog.dao.BookRepository;
import com.potatospy.bookcatalog.exceptions.StorageException;
import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/* I've tasked BookService with:
1. Scanning the book directory for books and
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

    private File bookDirectory = new File("D:\\edu_repo\\ebooks_test\\");
    private File deleteDirectory = new File(bookDirectory+"\\DELETE\\");

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

        init();
    }

    // == INIT ==
    @Override
    public void init() {
        try {
            if(!bookDirectory.exists()) {
                Files.createDirectory(Paths.get(bookDirectory.toString()));
            }
            if(!deleteDirectory.exists()){
                Files.createDirectory(Paths.get(deleteDirectory.toString()));
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }


    // == Public methods ==


    /* Read Book Directory
    1. Delete all entries in the books table in DB and bookManager list
    2. If the file isn't a directory,
    3. Get meta data for each book,
    4. create each book,
    5. Save each book each to DB (and return DB row),
    6. Add each returned row to BookManager
    */
    @Override
    public void loadBooksFromDirectory() {

        bookRepository.deleteAll(); // Wipe DB  table
        bookManager.deleteAllBooks();   // Wipe books from memory

        for(final File fileEntry : bookDirectory.listFiles()){  // Todo catch Null pointer

            log.info(fileEntry.getName()+ "\t\t\t\t\t\t" + bookDirectory.getName()+"/"+ fileEntry.getName() + "\t" + LocalDate.now() + LocalTime.now());

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


    // Mark book for deletion and move it to BOOKDIRECTORY/DELETE
    @Override
    public void deleteBook(Book bookToDelete) { // Todo catch DELETE directory not exist

        String bookFileLoc = bookToDelete.getFileLoc();

        String moveFrom =bookDirectory + "\\" + bookFileLoc;
        String moveTo= deleteDirectory +"\\" + bookFileLoc;

        Path temp;
        try {
            temp = Files.move
                    (Paths.get(moveFrom),
                            Paths.get(moveTo));


            //debug
            if (temp == null) {
                log.info("Files.move returned null!");
            } else {
                log.info("Files.move returned " + temp.toString());
            }


            bookToDelete.setFileLoc(moveTo);        // Update book fileLoc
            bookManager.deleteBook(bookToDelete);   // Update bookManager memory copy
            bookRepository.save(bookToDelete);      // Update bookRepository DB copy

        }catch(FileSystemException e){
            log.info("deleteBook got FileSystemException: The file may be in use by another process.");
            e.printStackTrace();
            log.info("Couldn't move file from " + moveFrom +" to " + moveTo);
        }catch(IOException e){
            log.info("deleteBook got IOException:");
            e.printStackTrace();
            log.info("Couldn't move file from " + moveFrom +" to " + moveTo);
        }
    }


    @Override
    public Book getBook(int id) {
        return bookManager.getBook(id);
    }


    @Override
    public void updateBook(Book updatedBook) {

        bookManager.updateBook(updatedBook);
        bookRepository.save(updatedBook);
    }


    // getBooksFromDb
    // Delete memory copy of books
    // Get an iterable list of books from DB
    // Check the books that were in DB are still in directory
    // Adds all books from iterable list to BookManager's list
    // Returns the BookManager
    @Override
    public BookManager getBooksFromDb() {


        bookManager.deleteAllBooks();

        Iterable<Book> bookIterable = bookRepository.findAll();

        // Check all the books that DB has still exist in bookDirectory
        for(Book book: bookIterable){

            if(new File(bookDirectory+ "\\" + book.getFileLoc()).exists()) {

                log.info("Looked at file " + bookDirectory+ "\\" + book.getFileLoc());
                bookManager.addBook(book);
                log.info("Added book from DB to memory");
            } else {
                bookRepository.delete(book);
                log.info("Book in database did not exist in directory: " + book.getBookTitle());
            }
        }

        return bookManager;
    }


    @Override
    public List<Book> getBooksFromMemory() {
        return bookManager.getBooksFromMemory();
    }




    // Get and Set
    public File getBookDirectory() { return bookDirectory; }
    public void setBookDirectory(File bookDirectory) { this.bookDirectory = bookDirectory; }
}


























