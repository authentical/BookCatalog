package com.potatospy.bookcatalog.controller;


import com.potatospy.bookcatalog.model.Book;
import com.potatospy.bookcatalog.model.BookManager;
import com.potatospy.bookcatalog.service.BookService;
import com.potatospy.bookcatalog.util.AttributeNames;
import com.potatospy.bookcatalog.util.ViewNames;
import com.potatospy.bookcatalog.util.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public List<Book> bookData(){ return bookService.getBooksFromDb().getBooksFromMemory(); }


    // == Handler methods ==

    // Catalog Simple View
    @GetMapping(Mappings.CATALOG_SIMPLE)
    public String catalogSimple(Model model){

        log.info("catalogSimple method called");

        System.out.println(bookData());
        // This is adding the entire BookManager book list into the model.
        model.addAttribute(AttributeNames.BOOK_DATA, bookData());

        return ViewNames.CATALOG_SIMPLE;
    }


    // Catalog Detail View
    @GetMapping(Mappings.CATALOG_DETAIL)
    public String catalogDetail(Model model){

        log.info("catalogDetail method called");

        return ViewNames.CATALOG_DETAIL;
    }


    // Edit View
    @GetMapping(Mappings.EDIT)
    public String editBook(Model model){

        log.info("editBook method called");


        return ViewNames.EDIT;
    }
}
