package com.potatospy.bookcatalog.controller;


import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.service.BookService;
import com.potatospy.bookcatalog.util.AttributeNames;
import com.potatospy.bookcatalog.util.ViewNames;
import com.potatospy.bookcatalog.util.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.io.File;
import java.util.List;




@Slf4j
@Controller
public class BookCatalogController {


    // == Fields ==
    private final BookService bookService;

    @Autowired
    public BookCatalogController(BookService bookService){this.bookService = bookService; }


    // == Model attributes ==
    @ModelAttribute
    public List<Book> bookData(){ return bookService.getBooksFromMemory(); }

    public static final File bookDirectory= new File("D:\\edu_repo\\ebooks_test\\");



    // == Handler methods ==

    // Catalog Simple View
    @GetMapping(Mappings.CATALOG_SIMPLE)
    public String catalogSimple(Model model){

        log.info("catalogSimple method called");

        // This is adding the entire BookManager book list into the model.
        model.addAttribute(AttributeNames.BOOK_DATA, bookData());

        return ViewNames.CATALOG_SIMPLE;
    }


    // Catalog Detail View
    @GetMapping(Mappings.CATALOG_DETAIL)
    public String catalogDetail(Model model){

        log.info("catalogDetail method called");

        // This is adding the entire BookManager book list into the model.
        model.addAttribute(AttributeNames.BOOK_DATA, bookData());
        return ViewNames.CATALOG_DETAIL;
    }


    // Add Book View
    @GetMapping(Mappings.ADD_BOOK)
    public String addBook(Model model){

        log.info("addBook method called");

        return ViewNames.ADD_BOOK;
    }

    // Add Book post mapping
    @PostMapping(Mappings.ADD_BOOK)
    public String addBookSubmit(@ModelAttribute Book newBook){

        return ViewNames.CATALOG_DETAIL;
    }


    // Edit View
    @GetMapping(Mappings.EDIT_BOOK)
    public String editBook(@RequestParam int id, Model model){

        log.info("editing book with it = {}", id);

        Book editableBook = bookService.getBook(id);

        model.addAttribute(AttributeNames.BOOK, editableBook);

        return ViewNames.EDIT_BOOK;
    }

    // Edit Book post mapping
    @PostMapping(Mappings.EDIT_BOOK)
    public String editBookSubmit(@ModelAttribute Book editedBook){


        return ViewNames.CATALOG_DETAIL;
    }


    @GetMapping(Mappings.LOAD_BOOKS)
    public String loadBooks(Model model) {

        log.info("Load Books called");

        bookService.loadBooksFromDirectory(bookDirectory);

        model.addAttribute(AttributeNames.BOOK_DATA, bookData());

        return ViewNames.CATALOG_DETAIL;
    }
}
