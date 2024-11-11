package org.test.teamproject_back.dto.request;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.test.teamproject_back.entity.User;

@Data
public class ReqGeneratePasswordDto {
    private String name;
    private String username;
    private String phoneNumber;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .name(name)
                .username(username)
                .phoneNumber(phoneNumber)
                .password(bCryptPasswordEncoder.encode("1Q2w3e4r!!"))
                .build();
    }
}
