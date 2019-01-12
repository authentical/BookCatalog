package com.potatospy.bookcatalog.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class BookCatalogController {

    @ResponseBody   // This is passed to HttpResponse
    @GetMapping("demo")
    public String demo() {
        log.info("demo method called");
        return "Book Catalog";
    }


    @GetMapping("welcome")
    public String welcome(Model model){

        log.info("welcome method called");
        model.addAttribute("message", "Welcome to the Book Catalog");

        return "welcome";   // Return welcome.jsp
        /*
            spring.mvc.view.prefix= /WEB-INF/view/
            spring.mvc.view.suffix= .jsp
         */

    }
}
