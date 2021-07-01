package com.austral.jibberjabberusers.controllers;

import com.austral.jibberjabberusers.dto.CreateUserDto;
import com.austral.jibberjabberusers.dto.FollowUserRequestDto;
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
    public ReducedUserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @PostMapping("/edit/{userId}")
    public ReducedUserDto editUser(@RequestBody ReducedUserDto reducedUserDto, @PathVariable String userId) {
        return userService.editUser(reducedUserDto,userId);
    }

    @GetMapping("/by-username/{username}")
    public ReducedUserDto findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/follow")
    public void followUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userService.followUser(followUserRequestDto);
    }

    @PostMapping("/unfollow")
    public void unfollowUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userService.unfollowUser(followUserRequestDto);
    }
}
