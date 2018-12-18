package com.jeffborda.codefellowship.controllers;

import com.jeffborda.codefellowship.ApplicationUser;
import com.jeffborda.codefellowship.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ApplicationUserController {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ApplicationUserRepo appUserRepo;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String rootRoute() {

        return "index";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signUp() {

        return "signup";
    }

    @RequestMapping(value="/newUser", method=RequestMethod.POST)
    public RedirectView createUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String bio,
            @RequestParam String username,
            @RequestParam String password) {

        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        appUserRepo.save(newUser);

        return new RedirectView("/profile/" + newUser.id);
    }

    @RequestMapping(value="/profile/{id}", method=RequestMethod.GET)
    public String showProfile(@PathVariable Long id, Model m) {
        m.addAttribute("user", appUserRepo.findById(id).get());
        return "profile";
    }



}
