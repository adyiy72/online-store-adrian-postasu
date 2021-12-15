package com.onlinestore;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/test")
    public String getContent() {
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder // Obiect spring care ajuta la identificarea utilizatorului curent logat.
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails.getUsername();
    }
}
