package com.potatospy.bookcatalog;

import com.potatospy.bookcatalog.dao.BookRepository;
import com.potatospy.bookcatalog.dao.UserRepository;
import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories("com.potatospy.bookcatalog.dao")
@EntityScan("com.potatospy.bookcatalog.model")
public class BookCatalogApplication implements CommandLineRunner {



    @Autowired
    DataSource dataSource;  // DataSource config is in application.properties

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    // Run application
    // Spring calls run method in this class
    public static void main(String[] args) {
        SpringApplication.run(BookCatalogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { // Can I be more specific?



        // TODO: Remove...  TEST ACCESS TO DB
        ////////////////////////////////////////////////
        System.out.println("\n\n\nOur DataSource is = " + dataSource +"\n\n\n");

        Iterable<Book> bookList = bookRepository.findAll();

        for(Book book:bookList){
            System.out.println("BOOK: " + book.toString());
        }


        Iterable<User>
                userList = userRepository.findAll();

        for(User user:userList){
            System.out.println("USER: " + user.toString());
        }

        ////////////////////////////////////////////////

    }
}

