package com.austral.jibberjabberusers.dto;

import com.austral.jibberjabberusers.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private int followersCount;

    private int followingCount;

    boolean following;

    public static UserProfileDto fromUser(AppUser appUser, boolean following) {
        return new UserProfileDto(
                appUser.getId(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getFollowers().size(),
                appUser.getFollowing().size(),
                following
        );
    }
}
