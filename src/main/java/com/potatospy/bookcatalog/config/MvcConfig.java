package com.potatospy.bookcatalog.config;

import com.potatospy.bookcatalog.util.ViewNames;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// TODO So now I've got MvcConfig which maps some urls
// AND BookCatalogController which maps others........

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName(ViewNames.HOME);
        registry.addViewController("home").setViewName(ViewNames.HOME);
    }
}