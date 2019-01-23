package com.potatospy.bookcatalog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;


/* Book class backs the DB books table

From database:
describe books;

book_index	int(11)	NO	PRI		auto_increment  // Database takes care of index assignment
book_title	varchar(145)	NO
isbn	varchar(15)	YES
published_date	date	YES
file_loc	varchar(255)	YES
edition	varchar(45)	YES
publisher	varchar(75)	YES
authors	varchar(200)	YES
modified_date	varchar(255)	YES


Todo: Book gets index AFTER SAVE -> Save new book has to be a save and retrieve

 */




/*
@Entity annotation defines that a class can be mapped to a table.

https://stackoverflow.com/questions/29332907/what-is-the-exact-meaning-of-the-jpa-entity-annotation
 */



@Data      // Lombok annotation bundling @ToString, @Getter, @EqualsAndHashCode, @Setter, @RequiredArgsConstructor
@Entity
@Table(name="books")
@EqualsAndHashCode      // For comparing object to object
public class Book {

    // == Fields ==

    // TODO [1] "The GeneratedValue annotation may be applied to a primary key property or field of an entity..."
    // The DB auto-increments for new rows so why are people using this?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // [1]
    @Column(name="book_index")
    private Integer id;

    private String bookTitle;
    private String isbn;    // ISBN may have leading zeros so String is used
    private Date publishedDate;
    private String fileLoc;    // Should this be File instead of String
    private boolean markedForDeletion;
    private String edition;
    private String publisher;
    private String authors;
    private LocalDate modifiedDate;   // Date book was added to DB


    // == Constructor

    public Book(String bookTitle, String fileLoc, LocalDate modifiedDate){

        this.bookTitle = bookTitle;
        this.fileLoc = fileLoc;
        this.modifiedDate = modifiedDate;
    }

    // Since @RequiredArgsConstructor is included with @Data... will all these params become mandatory for creating a book?
    public Book(String bookTitle, String isbn, Date publishedDate, String fileLoc, String edition, String publisher, String authors, LocalDate modifiedDate) {

        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.fileLoc = fileLoc;
        this.edition = edition;
        this.publisher = publisher;
        this.authors = authors;
        this.modifiedDate = modifiedDate;
    }
}
