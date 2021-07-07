package com.austral.jibberjabberusers.services;

import com.austral.jibberjabberusers.dto.*;
import com.austral.jibberjabberusers.exceptions.BadRequestException;
import com.austral.jibberjabberusers.models.AppUser;
import com.austral.jibberjabberusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ReducedUserDto createUser(CreateUserDto createUserDto) {
        AppUser appUser = new AppUser(
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                createUserDto.getUsername(),
                createUserDto.getPassword(),
                createUserDto.getEmail()
                );
        AppUser savedAppUser = userRepository.save(appUser);
        return ReducedUserDto.fromUser(savedAppUser);
    }

    @Override
    public ReducedUserDto getUserById(String userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
        return ReducedUserDto.fromUser(appUser);
    }

    @Override
    public UserListingDto getAllUsers() {
        List<AppUser> appUsers = userRepository.findAll();
        return UserListingDto.fromUsersList(appUsers);
    }

    @Override
    public ReducedUserDto editUser(EditUserDto editUserDto, String userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        appUser.setEmail(editUserDto.getEmail());
        appUser.setFirstName(editUserDto.getFirstName());
        appUser.setLastName(editUserDto.getLastName());
        appUser.setUsername(editUserDto.getUsername());
        appUser.setPassword(editUserDto.getPassword());
        AppUser savedUser = userRepository.save(appUser);

        return ReducedUserDto.fromUser(savedUser);
    }

    @Override
    public ReducedUserDto findByUsername(String username) {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ReducedUserDto.fromUser(appUser);
    }

    @Override
    public void followUser(FollowUserRequestDto followUserRequestDto) {
        AppUser loggedUser = userRepository.findById(followUserRequestDto.getLoggedUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        AppUser userToFollow = userRepository.findById(followUserRequestDto.getFollowRequestId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userToFollow.addFollower(loggedUser.getId());
        loggedUser.addFollowing(userToFollow.getId());

        userRepository.save(loggedUser);
        userRepository.save(userToFollow);
    }

    @Override
    public void unfollowUser(FollowUserRequestDto followUserRequestDto) {
        AppUser loggedUser = userRepository.findById(followUserRequestDto.getLoggedUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        AppUser userToUnfollow = userRepository.findById(followUserRequestDto.getFollowRequestId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userToUnfollow.removeFollower(loggedUser.getId());
        loggedUser.removeFollowing(userToUnfollow.getId());

        userRepository.save(loggedUser);
        userRepository.save(userToUnfollow);
    }

    @Override
    public FollowingIdsDto getFollowingIds(String userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new FollowingIdsDto(user.getFollowing());
    }
}
