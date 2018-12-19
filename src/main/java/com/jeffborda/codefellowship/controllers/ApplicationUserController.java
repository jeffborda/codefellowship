package com.jeffborda.codefellowship.controllers;

import com.jeffborda.codefellowship.ApplicationUser;
import com.jeffborda.codefellowship.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

// Credit stack overflow - find http
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {
}


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

    @RequestMapping(value="/users/newuser", method=RequestMethod.POST)
    public RedirectView createUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth,
            @RequestParam String bio,
            @RequestParam String username,
            @RequestParam String password) {

        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        appUserRepo.save(newUser);

        return new RedirectView("/users/" + newUser.id);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String showProfile(@PathVariable Long id, Model m) {
        Optional<ApplicationUser> u = appUserRepo.findById(id);
        if(u.isPresent()) {
            m.addAttribute("user", appUserRepo.findById(id).get());
            return "profile";
        }
        throw new ResourceNotFoundException();

    }




}
