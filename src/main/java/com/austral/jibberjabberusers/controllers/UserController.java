package com.austral.jibberjabberusers.controllers;

import com.austral.jibberjabberusers.dto.CreateUserDto;
import com.austral.jibberjabberusers.dto.ReducedUserDto;
import com.austral.jibberjabberusers.dto.UserListingDto;
import com.austral.jibberjabberusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ReducedUserDto getById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/get-all")
    public UserListingDto getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ReducedUserDto createUse(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }
}
