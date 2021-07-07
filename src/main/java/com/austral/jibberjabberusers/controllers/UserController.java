package com.austral.jibberjabberusers.controllers;

import com.austral.jibberjabberusers.dto.*;
import com.austral.jibberjabberusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public ReducedUserDto editUser(@RequestBody EditUserDto editUserDto, @PathVariable String userId) {
        return userService.editUser(editUserDto,userId);
    }

    @GetMapping("/by-username/{username}")
    public UserProfileDto findByUsername(@PathVariable String username, @RequestHeader("userId") String userId) {
        return userService.findByUsername(username,userId);
    }

    @PostMapping("/follow")
    public void followUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userService.followUser(followUserRequestDto);
    }

    @PostMapping("/unfollow")
    public void unfollowUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userService.unfollowUser(followUserRequestDto);
    }

    @GetMapping("/get-following-ids/{userId}")
    public FollowingIdsDto getFollowingIds(@PathVariable String userId){
        return userService.getFollowingIds(userId);
    }

}
