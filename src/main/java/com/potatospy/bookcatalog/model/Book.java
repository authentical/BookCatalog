package com.potatospy.bookcatalog.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/* Book provides an interface between the application and
the dao for retrieving and storing books


==DESCRIBE bookcatalog.books;==
book_index	int(11)	NO	PRI		auto_increment
book_title	varchar(145)	NO	UNI
isbn	varchar(15)	NO	UNI
published_date	date	YES
file_type	varchar(5)	NO
edition	varchar(45)	YES
publisher	varchar(75)	YES
authors	varchar(200)	YES
 */




/*
@Entity annotation defines that a class can be mapped to a table.

https://stackoverflow.com/questions/29332907/what-is-the-exact-meaning-of-the-jpa-entity-annotation
 */
@Entity
@Table(name="books")
public class Book {


    private String book_title;
    private String isbn;    // ISBN may have leading zeros so String is used
    private Date published_date;
    private String file_type;
    private String edition;
    private String publisher;
    private String authors;


    // TODO [1] "The GeneratedValue annotation may be applied to a primary key property or field of an entity..."
    // The DB auto-increments for new rows so why is everyone using this?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // [1]
    @Column(name="book_index")
    private Integer book_index;
    public Integer getBook_index(){
        return book_index;
    }
    // Don't create a setter for book_index. DB manages primary key creation

    public String getBook_title() {
        return book_title;
    }
    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    // TODO Will this be sending the correct Date format for SQL? <-----------------------------------
    public Date getPublished_date() {
        return published_date;
    }
    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public String getFile_type() {
        return file_type;
    }
    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }


    @Override
    public String toString() {
        return "\n\nBook{" +
                "book_index=" + book_index +
                "book_title='" + book_title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", published_date=" + published_date +
                ", file_type='" + file_type + '\'' +
                ", edition='" + edition + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                '}';
    }
}
