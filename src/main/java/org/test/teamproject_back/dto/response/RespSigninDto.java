package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Role;

@Builder
@Data
public class RespSigninDto {
    private String expireDate;
    private String accessToken;
    private Role role;
}
