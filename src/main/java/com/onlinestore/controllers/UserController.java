package com.onlinestore.controllers;

import com.onlinestore.controllers.dto.ResponseUserDto;
import com.onlinestore.controllers.dto.UserDto;
import com.onlinestore.controllers.dto.UserLoginDto;
import com.onlinestore.entities.User;
import com.onlinestore.services.RoleService;
import com.onlinestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseUserDto save(@RequestBody UserDto userDto){
        User user = UserDto.toUser(userDto, roleService);
        User userDb = userService.save(user);
        return ResponseUserDto.toResponseUser(userDb);
    }

    @DeleteMapping(path = "/{email}")
    public HttpStatus delete(@PathVariable String email){
        userService.delete(email);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/{email}")
    public ResponseUserDto findUserByEmail(@PathVariable String email){
        User userDb = userService.findByEmail(email);
        return ResponseUserDto.toResponseUser(userDb);
    }

    @PostMapping(path = "/login")
    public UserLoginDto login(){
        User loggedUser = userService.getLoggedUser();
        return UserLoginDto.toUserLoginDto(loggedUser);
    }


}
