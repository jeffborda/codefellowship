package com.jeffborda.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ApplicationUserController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String rootRoute() {

        return "index";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signUp() {

        return "signup";
    }



}
