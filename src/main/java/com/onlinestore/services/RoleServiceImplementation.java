package com.onlinestore.services;

import com.onlinestore.entities.Role;
import com.onlinestore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        Role roleInDatabase = findByName(role.getName());
        if (roleInDatabase != null) {
            return roleInDatabase;
        }
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
