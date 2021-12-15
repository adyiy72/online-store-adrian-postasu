package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Address;
import com.onlinestore.entities.Role;
import com.onlinestore.entities.User;
import com.onlinestore.exceptions.ResourceMissingInDatabase;
import com.onlinestore.services.RoleService;

import java.util.List;

public class UserDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<Address> userAddress;
    private String roleName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<Address> userAddress) {
        this.userAddress = userAddress;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static User toUser(UserDto userDto, RoleService roleService) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        List<Address> addresses = userDto.getUserAddress();
        addresses.forEach(el -> el.setUser(user));
        //relatie bidirectionala, atat pe adrese trebuie sa setam userul
        //cat si pe user trebuie sa setam adresele
        user.setUserAddress(addresses);

        Role role = roleService.findByName(userDto.getRoleName());

        if (role == null) {
            throw new ResourceMissingInDatabase("Role was not found in database");
        }

        user.setRole(role);
        return user;
    }
}

/**
 *
 * {
 *     "firstName" : "",
 *     "lastName" : "",
 *     .
 *     .
 *     .
 *     "role" : {
 *         "id": "",
 *         "name": "",
 *         .
 *         .
 *         .
 *         "other_attributes" :""
 *     }
 * }
 * VS
 * {
 *     "firstName" : "",
 *     "lastName" : "",
 *     .
 *     .
 *     .
 *     "role" :  "ADMIN";
 * }
 **/