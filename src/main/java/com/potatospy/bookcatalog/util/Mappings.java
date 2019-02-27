package com.potatospy.bookcatalog.util;


// Mappings used for request methods
public class Mappings {

    // Welcome homepage
    public static final String HOME = "home";

    // Catalog views
    public static final String CATALOG_SIMPLE="catalog-simple";
    public static final String CATALOG_DETAIL = "catalog-detail";

    // Edit Book view
    public static final String EDIT_BOOK="edit-book";

    // Manually add a book
    public static final String ADD_BOOK="add-book";

    // Scan directory, get metadata and load books into DB and memory
    public static final String SCAN_AND_LOAD_BOOKS ="scan-and-load-books";

    // Load books from DB without scan
    public static final String LOAD_BOOKS_FROM_DB="load-books-from-db";

    // Mark a book for deletion and move it to DELETE directory
    public static final String DELETE_BOOK="delete-book";

    // Delete book from database
    public static final String CONFIRM_DELETE="confirm-delete";

    //== constructor
    private Mappings(){}


}
