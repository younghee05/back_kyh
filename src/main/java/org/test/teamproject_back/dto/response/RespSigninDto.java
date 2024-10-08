package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespSigninDto {
    private String expireDate;
    private String accessToken;
}
