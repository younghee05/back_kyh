package org.test.teamproject_back.dto.response;

import lombok.Builder;
import lombok.Data;
import org.test.teamproject_back.entity.Address;
import org.test.teamproject_back.entity.User;

@Builder
@Data
public class RespUserInfoDto {
    private Long userId;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String img;
    private Address address;
}
