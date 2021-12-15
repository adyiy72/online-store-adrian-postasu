package com.onlinestore.services;

import com.onlinestore.entities.Role;

public interface RoleService {

    Role save(Role role);
    Role findByName(String name);

}
