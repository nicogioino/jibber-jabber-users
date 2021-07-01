package com.austral.jibberjabberusers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserRequestDto {

    String loggedUserId;

    String followRequestId;
}
