package com.jeffborda.codefellowship.controllers;

import com.jeffborda.codefellowship.ApplicationUser;
import com.jeffborda.codefellowship.ApplicationUserRepository;
import com.jeffborda.codefellowship.Post;
import com.jeffborda.codefellowship.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private ApplicationUserRepository appUserRepo;
    @Autowired
    private PostRepository postRepo;

    @RequestMapping(value="/myprofile/newpost", method= RequestMethod.POST)
    public RedirectView createPost(@RequestParam String postBody, Principal p, Model m) {

        Post newPost = new Post(postBody, new Date());
        newPost.appUser = appUserRepo.findByUsername(p.toString()); //This is questionable
        postRepo.save(newPost);
        return new RedirectView("/myprofile");
    }
}
