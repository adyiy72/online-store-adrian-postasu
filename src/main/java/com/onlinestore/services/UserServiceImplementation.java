package com.onlinestore.services;

import com.onlinestore.entities.User;
import com.onlinestore.exceptions.ResourceAlreadyPresentInDatabase;
import com.onlinestore.exceptions.ResourceMissingInDatabase;
import com.onlinestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder; // folosim pentru a cripta parola

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(User user) {
        User userInDatabase = userRepository.findByEmail(user.getEmail());
        if (userInDatabase != null) {
            throw new ResourceAlreadyPresentInDatabase(String.format("User with %s email already exists.", user.getEmail()));
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceMissingInDatabase(String.format("User with email %s not found", email));
        }
        return user;
    }

    @Override
    public void delete(String email) {
        User user = findByEmail(email);
        userRepository.delete(user);
    }

    @Override
    public User getLoggedUser() {
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder // Obiect spring care ajuta la identificarea utilizatorului curent logat.
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return userRepository.findByEmail(userDetails.getUsername());
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }


    //metoda care vine din sprign security
    //utilizata pentru identificarea userului in baza de date.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
