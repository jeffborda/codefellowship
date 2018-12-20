package com.jeffborda.codefellowship.controllers;

import com.jeffborda.codefellowship.ApplicationUser;
import com.jeffborda.codefellowship.ApplicationUserRepository;
import com.jeffborda.codefellowship.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {
}


@Controller
public class ApplicationUserController {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ApplicationUserRepository appUserRepo;
    @Autowired
    private PostRepository postRepo;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String rootRoute() {

        return "index";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signUp() {

        return "signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public RedirectView createUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth,
            @RequestParam String bio,
            @RequestParam String username,
            @RequestParam String password) {

        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        appUserRepo.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping(value="/myprofile")
    public String showProfile(Principal p, Model m) {
        if(p != null) {
            ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
            m.addAttribute("user", appUserRepo.findById(user.id).get());
        }

        return "profile";
    }


    @GetMapping(value="/users/{id}")
    public String showProfile(@PathVariable Long id, Model m) {
        Optional<ApplicationUser> u = appUserRepo.findById(id);
        if(u.isPresent()) {
            m.addAttribute("user", appUserRepo.findById(id).get());
            return "profile";
        }
        throw new ResourceNotFoundException();

    }




}
