package com.wp.project.beautysalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping({"/home", "/"})
    public String homePage() {

        return "layout.html";
    }


    @GetMapping({"/about"})
    public String about_us() {

        return "about_us.html";
    }


}
