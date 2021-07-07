package com.austral.jibberjabberusers.services;

import com.austral.jibberjabberusers.dto.*;

import java.util.List;
import java.util.Set;

public interface UserService {

    ReducedUserDto createUser(CreateUserDto createUserDto);

    ReducedUserDto getUserById(String userId);

    UserListingDto getAllUsers();

    ReducedUserDto editUser(EditUserDto editUserDto, String userId);

    ReducedUserDto findByUsername(String username);

    void followUser(FollowUserRequestDto followUserRequestDto);

    void unfollowUser(FollowUserRequestDto followUserRequestDto);

    FollowingIdsDto getFollowingIds(String userId);

}
