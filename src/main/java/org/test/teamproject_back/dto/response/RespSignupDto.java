package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.User;

@Builder
@Data
public class RespSignupDto {
    private User user;
}
