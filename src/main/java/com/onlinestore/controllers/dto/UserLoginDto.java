package com.onlinestore.controllers.dto;

import com.onlinestore.entities.User;

public class UserLoginDto {

    private String name;
    private String email;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UserLoginDto toUserLoginDto(User user){
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail(user.getEmail());
        userLoginDto.setRole(user.getRole().getName());
        userLoginDto.setName(String.format("%s %s", user.getFirstName(), user.getLastName()));
        return userLoginDto;
    }
}
