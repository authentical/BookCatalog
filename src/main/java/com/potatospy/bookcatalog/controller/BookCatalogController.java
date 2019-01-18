package com.potatospy.bookcatalog.controller;


import com.potatospy.bookcatalog.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class BookCatalogController {


    @ResponseBody
    @GetMapping("home")
    public String home(Model model){

        log.info("home method called");
        model.addAttribute("message", "Welcome to the Book Catalog");

        return ViewNames.HOME;   // Return home.jsp
        /* See application.properties
            spring.mvc.view.prefix= /WEB-INF/view/
            spring.mvc.view.suffix= .jsp
         */

    }
}
