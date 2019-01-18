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
//
//    // Welcome/Homepage
//    // Todo: Test it always redirects here first
//    @GetMapping("Mappings.HOME")
//    public String home(Model model){
//
//        log.info("home method called");
//        model.addAttribute("message", "Welcome to the Book Catalog");
//        log.info("model ={}", model);
//
//        return ViewNames.HOME;   // Return home view (NOT the filename)
//    }


    // Catalog Simple View
    @GetMapping(Mappings.CATALOG_SIMPLE)
    public String catalogSimple(Model model){

        log.info("catalogSimple method called");


        return ViewNames.CATALOG_SIMPLE;
    }
}
