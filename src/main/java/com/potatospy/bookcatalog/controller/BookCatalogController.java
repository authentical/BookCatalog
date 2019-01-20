package com.potatospy.bookcatalog.controller;


import com.potatospy.bookcatalog.util.ViewNames;
import com.potatospy.bookcatalog.util.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class BookCatalogController {


    // == Request methods ==

    // Catalog Simple View
    @GetMapping(Mappings.CATALOG_SIMPLE)
    public String catalogSimple(Model model){

        log.info("catalogSimple method called");

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
