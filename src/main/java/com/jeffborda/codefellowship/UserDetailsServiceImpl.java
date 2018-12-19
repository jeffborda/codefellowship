package com.jeffborda.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser appUser = appUserRepo.findByUsername(username);

        if(appUser == null) {
            System.out.println("User: " + username + ", not found.");
            throw new UsernameNotFoundException("User: " + username + ", was not found in the database.");
        }

        System.out.println("Found user: " + appUser);

        return appUser;
    }
}
