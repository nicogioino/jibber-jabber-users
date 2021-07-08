package com.austral.jibberjabberusers.dto;

import com.austral.jibberjabberusers.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListingDto {

    List<ReducedUserWithFollowingDto> users;

    public static UserListingDto fromUsersList (List<ReducedUserWithFollowingDto> users) {
        return new UserListingDto(users);
    }
}
