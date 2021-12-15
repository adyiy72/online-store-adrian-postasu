package com.onlinestore.services;

import com.onlinestore.entities.User;

public interface UserService {
    User save(User user);
    User findByEmail(String email);
    User update(User user);
    void delete(String email);
    User getLoggedUser();

}
