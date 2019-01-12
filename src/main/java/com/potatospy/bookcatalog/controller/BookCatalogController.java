package com.potatospy.bookcatalog.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class BookCatalogController {

    @ResponseBody   // This is passed to HttpResponse
    @GetMapping("demo")
    public String demo() {
        log.info("demo method called");
        return "Hello spring boot";
    }
}
